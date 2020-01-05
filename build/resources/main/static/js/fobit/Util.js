function Util(){

    this.replaceInPosition = function(text, position, value) {
        return text.substring(0, position) + value + text.substring(position + 1, text.length);
    }

    this.leftPad = function(str, max) {
      str = str.toString();
      return str.length < max ? UTIL.leftPad("0" + str, max) : str;
    }

}

window.UTIL = new Util();