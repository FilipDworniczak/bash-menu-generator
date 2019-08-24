# Bash Menu Generator
Generates bash menu based on provided JSON menu map
## Example menu map
~~~
{
  "settings": {
    "exitKey": "exit"
  },
  "navigation": {
    "afterSelectionMessage": "Witaj. Wybierz opcję: ",
    "execute": "",
    "options": {
      "dynamicChoice": {},
      "choices": [
        {
          "entryKey": "1",
          "message": "Download videos",
          "afterSelectionMessage": "Provide id of video that you want to download: ",
          "execute": "./get-yt-id-list.sh",
          "options": {
            "dynamicChoice": {
              "executeParamName": "YT_ID",
              "execute": "./download.sh"
            },
            "choices": []
          }
        },
        {
          "entryKey": "2",
          "message": "Opcja2",
          "afterSelectionMessage": "Opcja2. Wybierz opcję: ",
          "execute": "./option2.sh",
          "options": {"dynamicChoice": {}, "choices": []}
        }
      ]
    }
  }
}

~~~
## Structure
```
class MenuMap {
  Settings settings;
  Choice navigation;
}
class Settings {
  String exitKey;
}
class Choice {
  String entryKey?;
  String message?;
  String afterSelectionMessage;
  String execute?;
  Options options;
}
class Options {
  DynamicChoice dynamicChoice?;
  List<Choice> choices?;
}
class DynamicChoice {
  String executeParamName;
  String execute;
}
```
## Generated bash menu concept:
```bash
#!/bin/bash

# Navigation choice functions:
# root:
function Choice_root() {
    while :
    do
        echo "Options:"
        echo "1 - Download videos"
        echo "2 - Opcja2"
        echo "exit - Exit"
        read -e -p "Witaj. Wybierz opcję:" bmg_navigation_input
        if [[ ! -z "$bmg_navigation_input" ]]; then
            if [[ $bmg_navigation_input = "1" ]]; then
                Choice_1
            elif [[ $bmg_navigation_input = "2" ]]; then
                Choice_2
            elif [[ $bmg_navigation_input = "exit" ]]; then
                exit
            else
                echo "Please choose an option"
            fi
        fi
    done
}
# 1:
function Choice_1() {
    ./get-yt-id-list.sh
    read -e -p "Provide id of video that you want to download" YT_ID
    ./download.sh $YT_ID
}
# 2:
function Choice_2() {
    ./option2.sh
    echo "Options:"
    echo "1 - message"
    # echo "..."
    echo "exit - Exit"
    read -e -p "Opcja2. Wybierz opcję:"  bmg_navigation_input_2
    if [[ ! -z "$bmg_navigation_input_2" ]]; then
        if [[ $bmg_navigation_input_2 = "1" ]]; then
            Choice_1
        elif [[ $bmg_navigation_input_2 = "2" ]]; then
            Choice_2
        elif [[ $bmg_navigation_input_{{navigation.options.choices[1].entryKey}} = "exit" ]]; then
            exit
        else
            echo "Please choose an option"
        fi
    fi
}
# 2 -> 1:
function Choice_21() {
    # ...
}

Choice_root

```
