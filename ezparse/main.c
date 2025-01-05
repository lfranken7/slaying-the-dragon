#include "global.h"
#include "init.h"
#include "parser.h"


int tokenval = NONE; /* Value of Token Attribute */
int lineno =1;

int main()
{
  init();
  parse();
  exit(0);
}
