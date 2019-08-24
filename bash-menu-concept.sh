#!/bin/bash

# Navigation choice functions:
# root:
function Choice_root() {
    # {{navigation.execute}}
    while :
    do
        echo "Options:"
        # echo "{{navigation.options.choices[0].entryKey}} - {{navigation.options.choices[0].message}}"
        echo "1 - Download videos"
        # echo "{{navigation.options.choices[1].entryKey}} - {{navigation.options.choices[1].message}}"
        echo "2 - Opcja2"
        # echo "..."
        # echo "{{navigation.settings.exitKey}} - Exit"
        echo "exit - Exit"
        # read -e -p "{{navigation.afterSelectionMessage}}" bmg_navigation_input
        read -e -p "Witaj. Wybierz opcję: " bmg_navigation_input
        if [[ ! -z "$bmg_navigation_input" ]]; then
            # if [[ $bmg_navigation_input = "{{navigation.options.choices[0].entryKey}}" ]]; then
            if [[ $bmg_navigation_input = "1" ]]; then
                # Choice_{{navigation.options.choices[0].entryKey}}
                Choice_1
            # elif [[ $bmg_navigation_input = "{{navigation.options.choices[1].entryKey}}" ]]; then
            elif [[ $bmg_navigation_input = "2" ]]; then
                # Choice_{{navigation.options.choices[1].entryKey}}
                Choice_2
            # elif [[ $bmg_navigation_input = "{{navigation.settings.exitKey}}" ]]; then
            elif [[ $bmg_navigation_input = "exit" ]]; then
                exit
            else
                echo "Please choose an option"
            fi
        fi
    done
}
# 1:
# function Choice_{{navigation.options.choices[0].entryKey}}() {
function Choice_1() {
    # {{navigation.options.choices[0].execute}}
    ./get-yt-id-list.sh
    # read -e -p "{{navigation.options.choices[0].afterSelectionMessage}}" {{navigation.options.choices[0].options.dynamicChoice.executeParamName}}
    read -e -p "Provide id of video that you want to download: " YT_ID
    # {{navigation.options.choices[0].options.dynamicChoice.execute}} ${{navigation.options.choices[0].options.dynamicChoice.executeParamName}}
    ./download.sh $YT_ID
}
# 2:
# function Choice_{{navigation.options.choices[1].entryKey}}() {
function Choice_2() {
    # {{navigation.options.choices[1].execute}}
    ./option2.sh
    echo "Options:"
    # echo "{{navigation.options.choices[1].entryKey}} - {{navigation.options.choices[1].message}}"
    echo "1 - message"
    # echo "..."
    # echo "{{navigation.settings.exitKey}} - Exit"
    echo "exit - Exit"
    # read -e -p "{{navigation.options.choices[1].afterSelectionMessage}}" bmg_navigation_input_{{navigation.options.choices[1].entryKey}}
    read -e -p "Opcja2. Wybierz opcję: "  bmg_navigation_input_2
    # if [[ ! -z "$bmg_navigation_input_{{navigation.options.choices[1].entryKey}}" ]]; then
    if [[ ! -z "$bmg_navigation_input_2" ]]; then
            # if [[ $bmg_navigation_input_{{navigation.options.choices[1].entryKey}} = "{{navigation.options.choices[1].options.choices[0].entryKey}}" ]]; then
            if [[ $bmg_navigation_input_2 = "1" ]]; then
                # Choice_{{navigation.options.choices[1].options.choices[0].entryKey}}}
                Choice_1
            # elif [[ $bmg_navigation_input_{{navigation.options.choices[1].entryKey}} = "{{navigation.options.choices[1].options.choices[1].entryKey}}" ]]; then
            elif [[ $bmg_navigation_input_2 = "2" ]]; then
                # Choice_{{navigation.options.choices[1].options.choices[1].entryKey}}}
                Choice_2
            # elif [[ $bmg_navigation_input_{{navigation.options.choices[1].entryKey}} = "{{navigation.settings.exitKey}}" ]]; then
            elif [[ $bmg_navigation_input_2 = "exit" ]]; then
                exit
            else
                echo "Please choose an option"
            fi
        fi
}
# 2 -> 1:
# function Choice_21() {
# }
# ...
Choice_root
