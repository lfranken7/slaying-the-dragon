#include "lexer.h"
#include "global.h"
#include "error.h"
#include "symbol.h"



char lexbuf[BSIZE];
extern int lineno;
extern int tokenval;

int lexan()
{
  int t;
  while(1) {
    t=getchar();
    // Remove White Space
    if(t == ' ' || t == '\t')
      continue;
    // Newlines increment the line number but otherwise do nothing
    else if ( t == '\n' )
    {
      lineno+=1;
      continue;
    }
    else if (isdigit(t))
    {
      ungetc(t,stdin);
      scanf("%d",&tokenval);
      return NUM;
    }
    // IDs are composed of Alphabet Letter followed by alphanumerics
    else if (isalpha(t))
    {
      int p, b = 0;
      // Grab the rest of the ID
      while( isalnum(t)) {
        lexbuf[b] = t;
        t = getchar();
        b+=1;
        if(b >= BSIZE)
          error("Compiler Error");
      }
      lexbuf[b] = EOS;
      if(t != EOF)
        ungetc(t,stdin);
      p = lookup(lexbuf);
      if(p == 0)
        p = insert(lexbuf,ID);
      tokenval = p;
      return symtable[p].token;
    }
    // End Of File -- nothing left to lexan
    else if( t == EOF )
      return DONE;
    // Unknown Token
    else {
      tokenval = NONE;
      return t;
    }
  }
}
