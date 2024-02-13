#!/bin/sh -l

# Check if the argument is provided
if [ $# -eq 0 ]; then
    echo "Please provide the name of the project to fuzz"
    exit 1
fi

fuzz_project() {
    input_string="$1"

    # Compare the input string with another string
    if [ "$input_string" = "libass" ]; then
        echo "Fuzzing libass"
        /AFLplusplus/afl-fuzz -i /libass/seeds -D -o /libass/out -x /libass/fuzz/ass.dict -- /libass/fuzz/fuzz
    else
        echo "Failed to find the project to fuzz"
    fi
}

fuzz_project "$1" &

postprocess_results() {
    /AFLplusplus/afl-whatsup -s -d /libass/out 
}

# Capture the PID of the background process
pid=$!

# Sleep for a short duration to allow the parallel process to start
sleep 5

# Main process sleeps for x minutes
sleep "$(($2 * 60))"

# Kill the parallel process
echo "Killing AFL++ process PID $pid"

kill "$pid"

echo "Main process is done."

postprocess_results