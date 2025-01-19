package com.dragon.lox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



// TODO: undo the static importing, it makes it unclear if
// methods/classes are defined here or elsewhere
import static com.dragon.lox.TokenType.*; 

class Scanner {
  private final String source;
  private final List<Token> tokens = new ArrayList<>();

  // Start flag
  private int start = 0;
  // Current Character number ( works as a character Pointer kinda )
  private int current = 0;
  private int line = 1;


  private static final Map<String, TokenType> keywords;

  static {
    keywords = new HashMap<>();
    keywords.put("and",    AND);
    keywords.put("class",  CLASS);
    keywords.put("else",   ELSE);
    keywords.put("false",  FALSE);
    keywords.put("for",    FOR);
    keywords.put("fun",    FUN);
    keywords.put("if",     IF);
    keywords.put("nil",    NIL);
    keywords.put("or",     OR);
    keywords.put("print",  PRINT);
    keywords.put("return", RETURN);
    keywords.put("super",  SUPER);
    keywords.put("this",   THIS);
    keywords.put("true",   TRUE);
    keywords.put("var",    VAR);
    keywords.put("while",  WHILE);
  }


  // Constructor
  Scanner(String source) {
    this.source = source;
  }

  List<Token> scanTokens() {
    while (!isAtEnd()) {
      // We are at the beginning of the next lexeme.
      start = current;
      scanToken();
    }

    tokens.add(new Token(EOF, "", null, line));
    return tokens;
  }

  private boolean isAtEnd() {
    return current >= source.length();
  }
 

  private void scanToken() {
    char c = advance();
    switch (c) {
      case '(': addToken(LEFT_PAREN); break;
      case ')': addToken(RIGHT_PAREN); break;
      case '{': addToken(LEFT_BRACE); break;
      case '}': addToken(RIGHT_BRACE); break;
      case ',': addToken(COMMA); break;
      case '.': addToken(DOT); break;
      case '-': addToken(MINUS); break;
      case '+': addToken(PLUS); break;
      case ';': addToken(SEMICOLON); break;
      case '*': addToken(STAR); break; 
      // usually results in a string literal 
      case '"': string(); break;
      
      // these are two characters symbols that require lookahead
      // to resolve
      case '!':
        addToken(match('=') ? BANG_EQUAL : BANG);
        break;
      case '=':
        addToken(match('=') ? EQUAL_EQUAL : EQUAL);
        break;
      case '<':
        addToken(match('=') ? LESS_EQUAL : LESS);
        break;
      case '>':
        addToken(match('=') ? GREATER_EQUAL : GREATER);
        break;
      // Comments and division is done with slash
      case '/':
        if (match('/')) {
          // A comment goes until the end of the line.
          while (peek() != '\n' && !isAtEnd()) advance();
        } else {
          addToken(SLASH);
        }
        break;

      case ' ':
      case '\r':
      case '\t':
        // Ignore whitespace.
        break;

      case '\n':
        line++;
        break;

      default:
        if (isDigit(c)) {
          number(); 
        } else if (isAlpha(c)) {
          identifier();
        } else { 
          Lox.error(line, "Unexpected Character.");
        }
        break;
    }
  }

  private void identifier() {
    while (isAlphaNumeric(peek())) advance();
    
    String text = source.substring(start, current);
    TokenType type = keywords.get(text);
    if (type == null) type = IDENTIFIER;
    addToken(type);
  }

  private boolean isAlpha(char c) {
    return (c >= 'a' && c <= 'z') ||
           (c >= 'A' && c <= 'Z') ||
            c == '_';
  }

  private boolean isAlphaNumeric(char c) {
    return isAlpha(c) || isDigit(c);
  }

  private boolean isDigit(char c) {
    return c >= '0' && c <= '9';
  }
 
  private void number() {
    while (isDigit(peek())) advance();
    
    // Lock for a fractional part.
    if(peek() == '.' && isDigit(peekNext())) {
      // Consume the "."
      advance();
      
      while (isDigit(peek())) advance();
    }
    // Use the java double parse utility to fetche the value for the token
    addToken(NUMBER, 
        Double.parseDouble(source.substring(start,current)));
  }

  private char peekNext() {
    if (current + 1 >= source.length()) return '\0';
    return source.charAt(current + 1);
  }
 
  // Moves to the next character in the file / buffer
  private char advance() {
    return source.charAt(current++);
  }

  private void addToken(TokenType type) {
    addToken(type, null);
  }

  private void addToken(TokenType type, Object literal) {
    String text = source.substring(start, current);
    tokens.add(new Token(type, text, literal, line));
  }
 
  // this will attempt to match an expected character by
  // using a lookahead
  private boolean match(char expected) {
    if (isAtEnd()) return false;
    if (source.charAt(current) != expected) return false;

    current++;
    return true;
  }

  
  private char peek() {
    if (isAtEnd()) return '\0';
    return source.charAt(current);
  }


  // this method will continuously advance the lexer
  // to the matching string literal marker " 
  // Not to be confused with the String class
  private void string() {
    // Keep looking ahead until you find a matched '"'
    while (peek() != '"' && !isAtEnd()) {
      if (peek() == '\n') line++;
      advance();
    }

    if (isAtEnd()) {
      Lox.error(line, "Unterminated string.");
      return;
    }

    // The closing ".
    advance();

    // Trim the surrounding quotes.
    String value = source.substring(start + 1, current - 1);
    // String Literals have a value stored as the string contents
    addToken(STRING, value);
  }


}
