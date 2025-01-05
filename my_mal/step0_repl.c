#include<stdio.h>
#include<stdint.h>
#include"readline.h"


static uint8_t exit = 0;

int mal_eval(char *readback);
int mal_print(char *readback);




int mal_eval(char *readback)
{
	return 0;
}

int mal_print(char *readback)
{
	printf(" %s",readback);
	return 0;
}


int mal_rep()
{
	char readback[MAX_INPUT_SIZE];
	uint8_t error = 0;	
	
	error = mal_readline(readback);
	if (error) {
		goto lab_err;
	}

	
	error = mal_eval(readback);
	if (error) {
		goto lab_err;
	}


	error = mal_print(readback);
	if (error) {
		goto lab_err;
	}


	// Return No errors
	return 0;

	// Return with Error
	lab_err:
		printf("mal_repl: error (%d)!\n\r",error);
		return error;

}




int main(int argc, char ** argv)
{
	// Starting The 

	while (!exit)
	{
		printf("\n\ruser> ");
		if(mal_rep()) {	
			break;
		}
	}

}



