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
    shift: '`'
  },
  'Digit2': {
    normal: '!',
    shift: 'Y'
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
    normal: ']',
    shift: '{'
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
    shift: '.'
  },
  'KeyW': {
    normal: 'c',
    shift: 'D'
  },
  'KeyE': {
    normal: 'e',
    shift: 'E'
  },
  'KeyR': {
    normal: 'b',
    shift: '>'
  },
  'KeyT': {
    normal: 'h',
    shift: 'P'
  },
  'KeyY': {
    normal: '3',
    shift: 'B'
  },
  'KeyU': {
    normal: 'p',
    shift: 'H'
  },
  'KeyI': {
    normal: '8',
    shift: '*'
  },
  'KeyO': {
    normal: 'd',
    shift: 'C'
  },
  'KeyP': {
    normal: '\\',
    shift: 'A'
  },
  'BracketLeft': {//[
    normal: 'q',
    shift: 'X'
  },
  'BracketRight': {//]
    normal: '2',
    shift: '@'
  },
  'Backslash': {// \
    normal: 'a',
    shift: '|'
  },
  'CapsLock': {
    normal: 'CapsLock'
  },
  'KeyA': {
    normal: '[',
    shift: '&'
  },
  'KeyS': {
    normal: '%',
    shift: 'O'
  },
  'KeyD': {
    normal: '7',
    shift: 'Z'
  },
  'KeyF': {
    normal: 'x',
    shift: 'Q'
  },
  'KeyG': {
    normal: '~',
    shift: 'o'
  },
  'KeyH': {
    normal: '9',
    shift: '('
  },
  'KeyJ': {
    normal: '\'',
    shift: '?'
  },
  'KeyK': {
    normal: 'j',
    shift: 'V'
  },
  'KeyL': {
    normal: 'l',
    shift: 'L'
  },
  'Semicolon': { //;
    normal: ';',
    shift: ':'
	},
	'Quote': { //'
	normal: '/',
	shift: '\"'
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
	shift: 'S'
	},
	'KeyV': {
	normal: '4',
	shift: '$'
	},
	'KeyB': {
	normal: 'N',
	shift: 'I'
	},
	'KeyN': {
	normal: 'w',
	shift: 'W'
	},
	'KeyM': {
	normal: 's',
	shift: 'R'
	},
	'Comma': {
	normal: 'n',
	shift: 'i'
	},
	'Period': {
	normal: 'u',
	shift: 'U'
	},
	'Slash': {
	normal: 'v',
	shift: 'J'
	}
	};
    var korea = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;

      function assignElement() {
        keyboardEl = document.querySelector("#keyboard");
        inputGroupEl = document.getElementById("#input-group");
        inputEl = document.querySelector("#userPassword");
        ShiftEl = document.querySelectorAll("#shift");

    }

    //각 쿼리 이벤트 넣기
    function addEvent() {
        keyboardEl.addEventListener("mousedown", onMouseDown.bind(this));
        document.addEventListener("mouseup", onMouseUp.bind(this));
        inputEl.addEventListener("focus", onKeyboard);
      	ShiftEl.forEach(function(shift) {
		    shift.addEventListener("mouseup", toggleShift);
		});
		inputEl.addEventListener("keydown",function(event){
		    if (
			    (event.keyCode >= 65 && event.keyCode <= 90) || // a-z
			    (event.keyCode >= 97 && event.keyCode <= 122) || // A-Z
			    (event.keyCode >= 48 && event.keyCode <= 57) || // 0-9
			    (event.keyCode === 64 || // @
			     event.keyCode === 35 || // #
			     event.keyCode === 36 || // $
			     event.keyCode === 37 || // %
			     event.keyCode === 94 || // ^
			     event.keyCode === 38 || // &
			     event.keyCode === 42 || // *
			     event.keyCode === 40 || // (
			     event.keyCode === 41    // )
			    )
			    || (/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣|\[\]|\\;'.,\/]/.test(event.key))
			  ){
		       event.preventDefault();
		       }
		});
		//키보드 나오는 함수
		inputEl.addEventListener("focusin", onKeyboard);

    }

//마우스 클릭 이벤트
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
            inputEl.value = "";
        }

        keyboardEl.querySelector(".active") ?.classList.remove("active");
    }

//마우스 클릭이벤트
    function onMouseDown(e) {
        e.target.closest("div.key") ?.classList.add("active");
    }

//가상키보드 열기
    function onKeyboard() {
        keyboardEl.style.display = 'block' ;
    }
    
//shift 변환
    function toggleShift() {
        shiftOn = !shiftOn; //누를 때 마다 바뀌게
    }

//대소문자 특수문자 출력
    function printKeyCode(dataCode) {

        if (dataCode in keyCodes) {
            const keyCode = keyCodes[dataCode];
            const output = shiftOn ? keyCode.shift : keyCode.normal;
            shiftOn = false;
            return output;
        }
    }
   
//input key입력 이벤트 막기
	function offInput(event){
		// a-z, A-Z
		  if ((keyCode >= 65 && keyCode <= 90) || (keyCode >= 97 && keyCode <= 122)) {
		    // 영문 알파벳 입력
		  }
		  // 0-9
		  else if (keyCode >= 48 && keyCode <= 57) {
		    // 숫자 입력
		  }
		  // 한글
		  else if ((keyCode >= 12592 && keyCode <= 12687) || (keyCode >= 44032 && keyCode <= 55215)) {
		    // 한글 입력
		  }
	}

    
    assignElement();
    addEvent();
    }
    

    
    Keyboard();