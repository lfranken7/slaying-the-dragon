#include "global.h"
#include "symbol.h"
#include "error.h"

#define STRMAX 999 /*size of lexemes array*/
#define SYMMAX 100 /*size of symtable*/

char lexemes [STRMAX];
int lastchar = -1;

struct entry symtable[SYMMAX];
int lastentry = 0;

int lookup(char* s)
{
  int p;
  for(p = lastentry; p > 0; p--)
    if(strcmp(symtable[p].lexptr, s) == 0)
      return p;
  return 0;
}

int insert (char * s, int tok)
{
  int len;
  len = strlen(s);
  if(lastentry + 1 >= SYMMAX)
    error("symbol table full");
  if(lastchar + len + 1 >= STRMAX)
    error("lexemes array full");
  lastentry++;

  symtable[lastentry].token = tok;
  symtable[lastentry].lexptr = &lexemes[lastchar+1];
  lastchar = lastchar + len + 1;
  strcpy(symtable[lastentry].lexptr, s);
  return lastentry;

}
