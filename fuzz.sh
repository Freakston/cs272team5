#!/bin/sh -l

# Check if the argument is provided
if [ $# -eq 0 ]; then
    echo "Please provide the name of the project to fuzz"
    exit 1
fi

# Assign the argument to a variable
input_string="$1"

echo "input=$input_string" >> $GITHUB_OUTPUT
# Compare the input string with another string
if [ "$input_string" = "libass" ]; then
    echo "Fuzzing libass"
    /AFLplusplus/afl-fuzz -i /libass/seeds -D -o /libass/out -x /libass/fuzz/ass.dict  -- /libass/fuzz/fuzz
else
    echo "Failed to find the project to fuzz"
fi
