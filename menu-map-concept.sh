#!/bin/bash

function Choice_root() {
while :
do
echo "Options"
echo "1 - Download videos"
echo "2 - Opcja2"
echo "exit - Exit"
read -e -p "Witaj. Wybierz opcję: " bmg_choice_root
if [[ ! -z "$bmg_choice_root" ]]; then
if [[ $bmg_choice_root = "1" ]]; then
Choice_1
elif [[ $bmg_choice_root = "2" ]]; then
Choice_2
elif [[ $bmg_choice = "exit" ]]; then
exit
else
echo "Please choose an option"
fi
fi
done
}
}
function Choice_1() {
./get-yt-id-list.sh
}
function Choice_2() {
./option2.sh
}
Choice_root