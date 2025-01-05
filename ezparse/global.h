#ifndef _GLOBAL_H
#define _GLOBAL_H

#include <stdio.h>
#include <ctype.h>
#include <stdlib.h>
#include <string.h>
#define BSIZE 128
#define NONE -1
#define EOS '\0'

#define NUM 256
#define DIV 257
#define MOD 258
#define ID 259
#define DONE 260

extern int tokenval; 
extern int lineno;

struct entry {
  char *lexptr;
  int token;
};

extern struct entry symtable[100];


#endif // _GLOBAL_H
