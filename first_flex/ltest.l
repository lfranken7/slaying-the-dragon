%{
	unsigned int charCount=0, wordCount = 0, lineCount = 0;
%}

word [^ \t\n]+
eol \n

%%
{word} { wordCount++; charCount += yyleng; }
{eol}  { charCount++; lineCount++; }
.      { charCount++; }

%%
int yywrap() { return 1; }


int main()
{
	yylex();
	printf("lc:%d wc:%d cc:%d\n",lineCount, wordCount, charCount);
}
