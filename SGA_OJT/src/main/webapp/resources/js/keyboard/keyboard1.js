function Keyboard() {
    var switchEl;
    var fontSelectEl;
    var containerEl;
    var keyboardEl;
    var inputGroupEl;
    var inputEl;
    var keyPress = false;
    var mouseDown = false;
    var shiftOn = false;

    
    
const keyCodes = {
  'Backquote': {
    normal: '0',
    shift: ')'
  },
  'Digit1': {
    normal: 'y',
    shift: 'Y'
  },
  'Digit2': {
    normal: '@',
    shift: '`'
  },
  'Digit3': {
    normal: '#',
    shift: 'z'
  },
  'Digit4': {
    normal: 'm',
    shift: 'M'
  },
  'Digit5': {
    normal: '5',
    shift: '%'
  },
  'Digit6': {
    normal: 't',
    shift: 'T'
  },
  'Digit7': {
    normal: 'k',
    shift: 'K'
  },
  'Digit8': {
    normal: 'u',
    shift: 'U'
  },
  'Digit9': {
    normal: 'g',
    shift: 'G'
  },
  'Digit0': {
    normal: 'f',
    shift: 'F'
  },
  'Minus': {
    normal: '_',
    shift: '-'
  },
  'Equal': {
    normal: '=',
    shift: '+'
  },
  'Backspace': {
    normal: 'Backspace'
  },
  'Tab': {
    normal: 'Tab'
  },
  'KeyQ': {
    normal: '6',
    shift: '^'
  },
  'KeyW': {
    normal: 'c',
    shift: 'C'
  },
  'KeyE': {
    normal: 'e',
    shift: 'E'
  },
  'KeyR': {
    normal: 'b',
    shift: 'B'
  },
  'KeyT': {
    normal: 'h',
    shift: 'H'
  },
  'KeyY': {
    normal: '3',
    shift: '#'
  },
  'KeyU': {
    normal: 'p',
    shift: 'P'
  },
  'KeyI': {
    normal: '8',
    shift: '*'
  },
  'KeyO': {
    normal: 'd',
    shift: 'D'
  },
  'KeyP': {
    normal: 'a',
    shift: 'A'
  },
  'BracketLeft': {
    normal: 'q',
    shift: 'Q'
  },
  'BracketRight': {
    normal: '2',
    shift: '@'
  },
  'Backslash': {
    normal: '\\',
    shift: '|'
  },
  'CapsLock': {
    normal: 'CapsLock'
  },
  'KeyA': {
    normal: 'z',
    shift: 'Z'
  },
  'KeyS': {
    normal: 'o',
    shift: 'O'
  },
  'KeyD': {
    normal: '7',
    shift: '&'
  },
  'KeyF': {
    normal: 'x',
    shift: 'X'
  },
  'KeyG': {
    normal: 'v',
    shift: 'V'
  },
  'KeyH': {
    normal: '9',
    shift: '('
  },
  'KeyJ': {
    normal: 'n',
    shift: 'N'
  },
  'KeyK': {
    normal: 'j',
    shift: 'J'
  },
  'KeyL': {
    normal: 'l',
    shift: 'L'
  },
  'Semicolon': {
    normal: ';',
    shift: ':'
	},
	'Quote': {
	normal: '/',
	shift: '?'
	},
	'Enter': {
	normal: 'Enter'
	},
	'ShiftLeft': {
	normal: 'Shift'
	},
	'KeyZ': {
	normal: '1',
	shift: '!'
	},
	'KeyX': {
	normal: ',',
	shift: '<'
	},
	'KeyC': {
	normal: 'r',
	shift: 'R'
	},
	'KeyV': {
	normal: '4',
	shift: '$'
	},
	'KeyB': {
	normal: 'i',
	shift: 'I'
	},
	'KeyN': {
	normal: 'w',
	shift: 'W'
	},
	'KeyM': {
	normal: 's',
	shift: 'S'
	},
	'Comma': {
	normal: 'n',
	shift: 'N'
	},
	'Period': {
	normal: 'u',
	shift: 'U'
	},
	'Slash': {
	normal: 'v',
	shift: 'V'
	}
	};
   
console.log(Object.values(keyCodes).map(key => key.shift).join(''));

    function assignElement() {
        keyboardEl = document.querySelector("#keyboard");
        inputGroupEl = document.getElementById("#input-group");
        inputEl = document.querySelector("#input");
        ShiftEl = document.querySelectorAll("#shift");

    }

    //각 쿼리 이벤트 넣기
    function addEvent() {
        inputEl.addEventListener("input", onInput);
        keyboardEl.addEventListener("mousedown", onMouseDown.bind(this));
        document.addEventListener("mouseup", onMouseUp.bind(this));
        inputEl.addEventListener("click", toggleKeyboard);
      	ShiftEl.forEach(function(shift) {
		    shift.addEventListener("mouseup", toggleShift);
		});
        

    }

    function onMouseUp(e) {
        mouseDown = false;
        var keyEl = e.target.closest("div.key"); // data
        var isActive = keyEl ?.classList.contains("active");
        var val = keyEl ?.dataset.val;

        var code = keyEl ?.dataset.code;

        if (isActive && !!val && (val !== "Space") && (val !== "Backspace") && (val !== "Enter")) {
            inputEl.value += printKeyCode(code); //값 입력
        }

        if (isActive && val == "Enter") {
            keyboardEl.style.display = "none";
        }

        if (isActive && val == "Backspace") {
            inputEl.value = inputEl.value.slice(0, -1);
        }
        if (isActive && val == "clear") {
            console.log("clear")
            inputEl.value = "";
        }

        keyboardEl.querySelector(".active") ?.classList.remove("active");
    }

    function onMouseDown(e) {
        if (keyPress) return;
        e.target.closest("div.key") ?.classList.add("active");
    }

    function onInput(e) {
        e.target.value = e.target.value.replace(/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/, "");
    }

    function toggleKeyboard() {
        if (!inputEl.matches(":focus")) return;
        keyboardEl.style.display = keyboardEl.style.display === 'none' ? 'block' : 'none';
    }

    function toggleShift() {
        console.log("shift");
        shiftOn = !shiftOn; //누를 때 마다 바뀌게
    }

    function printKeyCode(dataCode) {

        if (dataCode in keyCodes) {
            const keyCode = keyCodes[dataCode];
            const output = shiftOn ? keyCode.shift : keyCode.normal;
            shiftOn = false;
            return output;
        }
    }
    
    function remapKeyCodes() {
		  const normalValues = Object.values(keyCodes).map(obj => obj.normal);
		  const shiftValues = Object.values(keyCodes).map(obj => obj.shift).filter(value => value);
		
		  const shuffledNormalValues = shuffle(normalValues);
		  const shuffledShiftValues = shuffle(shiftValues);
		
		  const newKeyCodes = {};
		  Object.keys(keyCodes).forEach(key => {
		    const normalValue = shuffledNormalValues.pop();
		    let shiftValue = keyCodes[key].shift;
		    if (shiftValue) {
		      shiftValue = shuffledShiftValues.pop();
		    }
		    newKeyCodes[key] = { normal: normalValue, shift: shiftValue };
		  });
		
		  return newKeyCodes;
		}
		
		function shuffle(array) {
		  const shuffledArray = [...array];
		  for (let i = shuffledArray.length - 1; i > 0; i--) {
		    const j = Math.floor(Math.random() * (i + 1));
		    [shuffledArray[i], shuffledArray[j]] = [shuffledArray[j], shuffledArray[i]];
		  }
		  return shuffledArray;
		}
    const newKeyCodes = remapKeyCodes();
	console.log(newKeyCodes);
    
    assignElement();
    addEvent();
    }
    

    
    Keyboard();