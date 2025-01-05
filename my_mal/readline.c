// BONUS POINTS FOR THE READLINE FFI (Foreign Function Interface)
// Hope we like reading haha
#include <stdio.h>
#include <stdint.h>
#include "readline.h"

#ifndef GNU_READLINE

int mal_readline(char *readback)
{
	if(fgets(readback, MAX_INPUT_SIZE, stdin) == NULL)
	{
		//TODO: LOG ERROR
		return 1;
	}

	return 0;
}

#else

int mal_readline(char *readback)
{
    // Not implemented yet
    return 1;
}

#endif
