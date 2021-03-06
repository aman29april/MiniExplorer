/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mm;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.undo.*;
import javax.swing.event.*;
import java.awt.event.*;


class jpe extends DefaultStyledDocument {

private DefaultStyledDocument doc;
private Element rootElement;

int index;

private boolean multiLineComment;
private MutableAttributeSet normal;
private MutableAttributeSet keyword;
private MutableAttributeSet comment;
private MutableAttributeSet quote;
private MutableAttributeSet method;
private MutableAttributeSet variable;


private Hashtable keywords;
private Hashtable methodhash;

public jpe() {
doc = this;
rootElement = doc.getDefaultRootElement();
putProperty(DefaultEditorKit.EndOfLineStringProperty, "\n");

normal = new SimpleAttributeSet();
StyleConstants.setForeground(normal, Color.black);

comment = new SimpleAttributeSet();
StyleConstants.setForeground(comment, Color.gray);
StyleConstants.setItalic(comment, true);

keyword = new SimpleAttributeSet();
StyleConstants.setForeground(keyword, Color.blue);

method = new SimpleAttributeSet();
StyleConstants.setForeground(method, Color.magenta);

variable = new SimpleAttributeSet();
StyleConstants.setForeground(method, Color.magenta);

quote = new SimpleAttributeSet();
StyleConstants.setForeground(quote, Color.red);

//Keywords like double, for, if ....
Object keywordObject = new Object();
keywords = new Hashtable();
keywords.put("abstract", keywordObject);
keywords.put("boolean", keywordObject);
keywords.put("break", keywordObject);
keywords.put("byte", keywordObject);
keywords.put("byvalue", keywordObject);
keywords.put("case", keywordObject);
keywords.put("cast", keywordObject);
keywords.put("catch", keywordObject);
keywords.put("char", keywordObject);
keywords.put("class", keywordObject);
keywords.put("const", keywordObject);
keywords.put("continue", keywordObject);
keywords.put("default", keywordObject);
keywords.put("do", keywordObject);
keywords.put("double", keywordObject);
keywords.put("else", keywordObject);
keywords.put("extends", keywordObject);
keywords.put("false", keywordObject);
keywords.put("final", keywordObject);
keywords.put("finally", keywordObject);
keywords.put("float", keywordObject);
keywords.put("for", keywordObject);
keywords.put("future", keywordObject);
keywords.put("generic", keywordObject);
keywords.put("goto", keywordObject);
keywords.put("if", keywordObject);
keywords.put("implements", keywordObject);
keywords.put("import", keywordObject);
keywords.put("inner", keywordObject);
keywords.put("instanceof", keywordObject);
keywords.put("int", keywordObject);
keywords.put("interface", keywordObject);
keywords.put("long", keywordObject);
keywords.put("native", keywordObject);
keywords.put("new", keywordObject);
keywords.put("null", keywordObject);
keywords.put("operator", keywordObject);
keywords.put("outer", keywordObject);
keywords.put("package", keywordObject);
keywords.put("private", keywordObject);
keywords.put("protected", keywordObject);
keywords.put("public", keywordObject);
keywords.put("rest", keywordObject);
keywords.put("return", keywordObject);
keywords.put("short", keywordObject);
keywords.put("static", keywordObject);
keywords.put("String", keywordObject);
keywords.put("super", keywordObject);
keywords.put("switch", keywordObject);
keywords.put("synchronized", keywordObject);
keywords.put("this", keywordObject);
keywords.put("throw", keywordObject);
keywords.put("throws", keywordObject);
keywords.put("transient", keywordObject);
keywords.put("true", keywordObject);
keywords.put("try", keywordObject);
keywords.put("var", keywordObject);
keywords.put("virtual", keywordObject);
keywords.put("void", keywordObject);
keywords.put("volatile", keywordObject);
keywords.put("while", keywordObject);

//"Methods" like procedure in Pascal or sub in Perl
Object methodObject = new Object();
methodhash = new Hashtable();
methodhash.put("sub", methodObject);
methodhash.put("procedure", methodObject);
methodhash.put("function", methodObject);
methodhash.put("union", methodObject);
}

public void insertString(int offset, String str,
AttributeSet a)
throws BadLocationException {
if (str.equals("{"))
str = addMatchingBrace(offset);

super.insertString(offset, str, a);
processChangedLines(offset, str.length());
}
public void remove(int offset, int length) throws
BadLocationException {
super.remove(offset, length);
processChangedLines(offset, 0);
}

/*
* Determine how many lines have been changed,
* then apply highlighting to each line
*/
private void processChangedLines(int offset, int length)
throws BadLocationException {
String content = doc.getText(0, doc.getLength());

// The lines affected by the latest document update
int startLine = rootElement.getElementIndex(offset);
int endLine = rootElement.getElementIndex(offset + length);


// Do the actual highlighting
for (int i = startLine; i <= endLine; i++) {
applyHighlighting(content, i);
}

// Resolve highlighting to the next end multi line delimiter
if (isMultiLineComment())
commentLinesAfter(content, endLine);
else
highlightLinesAfter(content, endLine);
}

/*
* Highlight lines when a multi line comment is still 'open'
* (ie. matching end delimiter has not yet been encountered)
*/
private boolean commentLinesBefore(String content, int line) {
int offset = rootElement.getElement(line).getStartOffset();

// Start of comment not found, nothing to do
int startDelimiter =
lastIndexOf(content, getStartDelimiter(), offset - 2);

if (startDelimiter < 0)
return false;

// Matching start/end of comment found, nothing to do
int endDelimiter =
indexOf(content, getEndDelimiter(), startDelimiter);

if (endDelimiter < offset & endDelimiter != -1)
return false;

// End of comment not found, highlight the lines
doc.setCharacterAttributes(
startDelimiter,
offset - startDelimiter + 1,
comment,
false);
return true;
}

/*
* Highlight comment lines to matching end delimiter
*/
private void commentLinesAfter(String content, int line) {
int offset = rootElement.getElement(line).getEndOffset();

// End of comment not found, nothing to do
int endDelimiter = indexOf(content, getEndDelimiter(), offset);

if (endDelimiter < 0)
return;

// Matching start/end of comment found, comment the lines
int startDelimiter =
lastIndexOf(content, getStartDelimiter(), endDelimiter);

if (startDelimiter < 0 || startDelimiter <= offset) {
doc.setCharacterAttributes(
offset,
endDelimiter - offset + 1,
comment,
false);
}
}

/*
* Highlight lines to start or end delimiter
*/
private void highlightLinesAfter(String content, int line)
throws BadLocationException {
int offset = rootElement.getElement(line).getEndOffset();

// Start/End delimiter not found, nothing to do

int startDelimiter =
indexOf(content, getStartDelimiter(),
offset);
int endDelimiter = indexOf(content, getEndDelimiter(), offset);

if (startDelimiter < 0)
startDelimiter = content.length();

if (endDelimiter < 0)
endDelimiter = content.length();

int delimiter = Math.min(startDelimiter, endDelimiter);

if (delimiter < offset)
return;

// Start/End delimiter found, reapply highlighting
int endLine = rootElement.getElementIndex(delimiter);

for (int i = line + 1; i < endLine; i++) {
Element branch = rootElement.getElement(i);
Element leaf =

doc.getCharacterElement(branch.getStartOffset());
AttributeSet as = leaf.getAttributes();

if (as.isEqual(comment))
applyHighlighting(content, i);
}
}

/*
* Parse the line to determine the appropriate highlighting
*/
private void applyHighlighting(String content, int line)
throws BadLocationException {

int startOffset = rootElement.getElement(line).getStartOffset();
int endOffset = rootElement.getElement(line).getEndOffset() - 1;

int lineLength = endOffset - startOffset;
int contentLength = content.length();

if (endOffset >= contentLength)
endOffset = contentLength - 1;

// check for multi line comments
// (always set the comment attribute for the entire line)
if (endingMultiLineComment(content, startOffset, endOffset)
|| isMultiLineComment()
|| startingMultiLineComment(content, startOffset, endOffset)) {
doc.setCharacterAttributes(
startOffset,
endOffset - startOffset + 1,
comment,
false);
return;
}

// set normal attributes for the line
doc.setCharacterAttributes(startOffset,
lineLength, normal, true);

// check for single line comment
int index =
content.indexOf(getSingleLineDelimiter(),
startOffset);

if ((index > -1) && (index < endOffset)) {
doc.setCharacterAttributes(
index,
endOffset - index + 1,
comment,
false);
endOffset = index - 1;
}

// check for tokens
checkForTokens(content, startOffset, endOffset);
}

/*
* Does this line contain the start delimiter
*/
private boolean startingMultiLineComment(
String content,
int startOffset,
int endOffset)
throws BadLocationException {
int index = indexOf(content, getStartDelimiter(), startOffset);

if ((index < 0) || (index > endOffset))
return false;
else {
setMultiLineComment(true);
return true;
}
}

/*
* Does this line contain the end delimiter
*/
private boolean endingMultiLineComment(
String content,
int startOffset,
int endOffset)
throws BadLocationException {
int index = indexOf(content, getEndDelimiter(), startOffset);

if ((index < 0) || (index > endOffset))
return false;
else {
setMultiLineComment(false);
return true;
}
}

/*
* We have found a start delimiter
* and are still searching for the end delimiter
*/
private boolean isMultiLineComment() {
return multiLineComment;
}

private void setMultiLineComment(boolean value) {
multiLineComment = value;
}

/*
* Parse the line for tokens to highlight
*/
private void checkForTokens(
String content,
int startOffset,
int endOffset) {
while (startOffset <= endOffset) {

while (isDelimiter(content
.substring(startOffset, startOffset + 1))) {
if (startOffset < endOffset)
startOffset++;
else
return;
}

// Extract and process the entire token
if (isQuoteDelimiter(content
.substring(startOffset, startOffset + 1)))
startOffset =
getQuoteToken(content, startOffset,
endOffset);
else
startOffset =
getOtherToken(content, startOffset,
endOffset);
}
}

private int getQuoteToken(String content,
int startOffset, int endOffset) {
String quoteDelimiter =
content.substring(startOffset, startOffset + 1);
String escapeString = getEscapeString(quoteDelimiter);

int index;
int endOfQuote = startOffset;

// skip over the escape quotes in this quote
index = content.indexOf(escapeString, endOfQuote + 1);

while ((index > -1) && (index < endOffset)) {
endOfQuote = index + 1;
index = content.indexOf(escapeString, endOfQuote);
}

// now find the matching delimiter
index = content.indexOf(quoteDelimiter, endOfQuote + 1);

if ((index < 0) || (index > endOffset))
endOfQuote = endOffset;
else
endOfQuote = index;

doc.setCharacterAttributes(
startOffset,
endOfQuote - startOffset + 1,
quote,
false);

return endOfQuote + 1;
}


private int getOtherToken(String content,
int startOffset, int endOffset) {
int endOfToken = startOffset + 1;

while (endOfToken <= endOffset) {
if (isDelimiter(content.substring(endOfToken, endOfToken + 1)))
break;

endOfToken++;
}

String token = content.substring(startOffset, endOfToken);


//Keyword? --> see keywordhash for supported keywords
if (isKeyword(token))
doc.setCharacterAttributes(
startOffset,
endOfToken - startOffset,
keyword,
false);

//Method? --> see methodhash for supported names
if (isMethod(token)||token.startsWith("@")

||token.startsWith("$#")||token.startsWith("$"))
doc.setCharacterAttributes(
startOffset,
endOfToken - startOffset,
method,
false);

return endOfToken + 1;
}

/*
* Assume the needle will the found at the start/end of the line
*/
private int indexOf(String content, String needle, int offset) {
int index;

while ((index = content.indexOf(needle, offset)) != -1) {
String text = getLine(content, index).trim();

if (text.startsWith(needle) || text.endsWith(needle))
break;
else
offset = index + 1;
}

return index;
}

/*
* Assume the needle will the found at the start/end of the line
*/
private int lastIndexOf(String content, String needle, int offset) {
int index;

while ((index = content.lastIndexOf(needle, offset)) != -1) {
String text = getLine(content, index).trim();

if (text.startsWith(needle) || text.endsWith(needle))
break;
else
offset = index - 1;
}

return index;
}

private String getLine(String content, int offset) {
int line = rootElement.getElementIndex(offset);
Element lineElement = rootElement.getElement(line);
int start = lineElement.getStartOffset();
int end = lineElement.getEndOffset();
return content.substring(start, end - 1);
}

/*
* Override for other languages
*/
protected boolean isDelimiter(String character) {
String operands = ";:{}()[]+-/%<=>!&|^~*";

if (Character.isWhitespace(character.charAt(0))
|| operands.indexOf(character) != -1)
return true;
else
return false;
}

/*
* Override for other languages
*/
protected boolean isQuoteDelimiter(String character) {
String quoteDelimiters = "\"'";

if (quoteDelimiters.indexOf(character) < 0)
return false;
else
return true;
}


protected boolean isKeyword(String token) {
Object o = keywords.get(token);

return o == null ? false : true;
}

protected boolean isMethod(String token) {
Object o1 = methodhash.get(token);

return o1 == null ? false : true;
}

/*
* Override for other languages
*/
protected String getStartDelimiter() {
return "/*";
}

/*
* Override for other languages
*/
protected String getEndDelimiter() {
return "*/";
}

/*
* Override for other languages
*/
protected String getSingleLineDelimiter() {
return "//";
}

/*
* Override for other languages
*/
protected String getEscapeString(String quoteDelimiter) {
return "\\" + quoteDelimiter;
}

/*
*
*/
protected String addMatchingBrace(int offset)
throws BadLocationException {
StringBuffer whiteSpace = new StringBuffer();
int line = rootElement.getElementIndex(offset);
int i = rootElement.getElement(line).getStartOffset();

while (true) {
String temp = doc.getText(i, 1);

if (temp.equals(" ") || temp.equals("\t")) {
whiteSpace.append(temp);
i++;
} else
break;
}

return "{\n"
+ whiteSpace.toString()
+ "\t\n"
+ whiteSpace.toString()
+ "}";
}

public static void main(String a[]) {


JFrame mjf = new JFrame();
JEditorPane textcomp = new JEditorPane();



final UndoManager undo = new UndoManager();


textcomp.setEditorKit(new StyledEditorKit());
//textcomp.setDocument(new SyntaxDocument());

JScrollPane scroll = new JScrollPane(textcomp);

Document doc1 = textcomp.getDocument();



doc1.addUndoableEditListener(new UndoableEditListener() {
public void undoableEditHappened(UndoableEditEvent evt) {

undo.addEdit(evt.getEdit());
}
});

textcomp.getActionMap().put("Undo",
new AbstractAction("Undo") {
public void actionPerformed(ActionEvent evt) {
try {
if (undo.canUndo()) {
undo.undo();
}
} catch (CannotUndoException e) {
}
}
});

textcomp.getInputMap().put(KeyStroke.getKeyStroke( "control Z"),
"Undo");

textcomp.getActionMap().put("Redo",
new AbstractAction("Redo") {
public void actionPerformed(ActionEvent evt) {
try {
if (undo.canRedo()) {
undo.redo();
}
} catch (CannotRedoException e) {
}
}
});


textcomp.getInputMap().put(KeyStroke.getKeyStroke( "control Y"),
"Redo");



mjf.getContentPane().add(scroll);
mjf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
mjf.setSize(600, 300);
mjf.setVisible(true);
}
}
