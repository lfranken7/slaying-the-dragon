%{
	#include"y.lexemes.h"
%}

%pointer

digit	[0-9]
letter	[a-zA-Z]

ws [ \t\n\r]

%%

"+"	{ return PLUS; }
"-"	{ return MINUS; }
"*"	{ return TIMES; }
"/"	{ return FSLASH; }
"("	{ return LPAREN; }
")"	{ return RPAREN; }


{digit}+	{ //yylval.num = atoi(yytext); 
		  return NUMBER; } 

{ws}+		/* do nothing with ws */

";;.*"		/* this is a comment remove the line */

"."		{ 	printf("Unrecognized Lexeme: %s", yytext);
			return -1; }


%%
int yywrap()
{
	// TODO: make this more complicated I think.
	return 1;
}


int main()
{
	yylex();
}

