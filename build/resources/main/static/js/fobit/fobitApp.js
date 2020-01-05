var app = angular.module('fobitApp', [])

app.filter('trustUrlFilter', ['$sce', function ($sce) {
  return function(cardId) {
    var url = "/images/cards/" + cardId + ".png"
    url = $sce.trustAsResourceUrl(url)
    return url
  };
}]);

app.controller('FobitController', ['$scope', '$http', '$timeout', '$location', function ($scope, $http, $timeout, $location) {

  var model = this
  var t = this
  model.cards = []
  model.choices = []
  model.report = []
  model.binaryValue = UTIL.leftPad(0, 32)
  model.hexaValue = UTIL.leftPad(0, 8)
  model.tookTime
  model.score
  model.scores = []
  model.screen = 'loading'
  model.tab = 'report'
  model.fighting = true
  model.loading = true
  model.zooming = false
  model.zoomCard = null
  model.level = 1

  var KEY_ENTER = 13
  var KEY_SPACE = 32
  var KEY_HOME = 104
  var KEY_FIGHT = 102
  var KEY_RESET = 114
  var KEY_CARDS = 99

  var STATUS_ICONS_MAP = {
    'BEGIN': 'bolt',
    'ATTACK': 'arrow-right',
    'DEFEND': 'shield',
    'VICTORY!': 'flag',
    'YOU LOSE': 'warning'
  }

  this.init = function() {
    model.tookTime = 0
    model.score = 0
    model.report.currentRound = -1
    model.hexaValue = $location.$$hash || UTIL.leftPad(0, 8)
    loadCards()

    addEventListener("load", function(event) {
      l('All loaded')
      delay(600, function(){
        model.screen = 'welcome'
        $scope.$apply()
      })
    })
  }

  this.restart = function(e) {
    DB.clear()
    this.reset()
    this.updateLevel(1)
  }

  this.keyPress = function(e) {
    var code = e.which
    if (this.screen == 'welcome') {
      this.keyPressWelcome(code);
    } else if (this.screen == 'game') {
      this.keyPressGame(code);
    }
    e.preventDefault();
    return false;
  }

  this.keyPressWelcome = function(code){
    if (code == KEY_ENTER) {
      this.changeScreen('game')
    }
  }

  this.keyPressGame = function(code){
    if (this.fighting) {
      if (code == KEY_FIGHT || code == KEY_SPACE) {
        this.fight()
      }
    } else {
      if (code == KEY_CARDS || code == KEY_SPACE) {
        this.showCards()
      }
    }

    if (code == KEY_HOME) {
      this.showCards()
      this.changeScreen('welcome')
    } else if (code == KEY_RESET) {
      this.reset()
    }
  }

  this.changeScreen = function(screen) {
    delay(0, function(){
      if (screen == 'welcome') {
        model.fighting = true;
        model.zooming = false;
      }
      t.screen = screen
    })
  }

  this.fight = function() {
    model.level = DB.getLevel() ? DB.getLevel() : 1
    var url = "/api/battle/" + model.level + "/" + model.hexaValue

    model.loading = true
    var timeBefore = new Date().getTime()
    $http.get(url).then(function(response){
      model.tookTime = new Date().getTime() - timeBefore
      delay(0, function(){
        model.report = response.data
        model.score = model.report.score
        model.fighting = false

        DB.addScore(model.hexaValue, model.score)
        model.scores = DB.getScores()

        t.updateLevel(response.data.level)
      })
    })
  }

  this.levelAdvance = function() {
    var newLevel = model.level + 1
    if (newLevel >= 10) {
      newLevel = 10
    }
    this.updateLevel(newLevel)
    this.showCards()
    DB.clearScores();
  }

  this.updateLevel = function(level) {
      model.level = level
      DB.addLevel(level)
  }

  this.replay = function(combination) {
    model.hexaValue = combination
    updateRowsWithHexaValue()
    updateHashInUrl()
    this.showCards()
  }

  this.reset = function() {
    $location.hash("")
    this.init()
  }

  this.showCards = function() {
    model.fighting = true
  }

  this.selectCard = function(card) {
    var id = card.id
    model.choices[id] = !model.choices[id]
    updateBinaryValue(id, model.choices[id])
    updateHexaValue()
    t.zoomOff()
  }

  this.zoomOn = function(card) {
    model.zooming = true
    model.zoomCard = card
  }

  this.zoomOff = function() {
    model.zooming = false
  }

  this.cardDenied = function(card){
    return card.level > model.level
  }

  this.getRoundStatus = function(index, log){
    if (index == 0) {
      return "BEGIN"
    }
    if (!log.computerPlayer.alive) {
      return "VICTORY!"
    }
    if (!log.humanPlayer.alive) {
      return "YOU LOSE"
    }
    if (log.computerPlayer.attacking) {
      return "ATTACK"
    }
    return "DEFEND"
  }

  this.getRoundStatusClass = function(index, log){
    return "oi-" + STATUS_ICONS_MAP[this.getRoundStatus(index, log)]
  }

  function updateBinaryValue(rowId, selected){
    var charToReplace = selected ? "1" : "0"
    model.binaryValue = UTIL.replaceInPosition(model.binaryValue, 32 - rowId, charToReplace)
  }

  function updateHexaValue(){
    var hexaValue = parseInt(model.binaryValue, 2).toString(16).toUpperCase()
    model.hexaValue = UTIL.leftPad(hexaValue, 8)
    updateHashInUrl()
  }

  function updateHashInUrl(){
    $location.hash(model.hexaValue)
  }

  function loadCards(){
    l('Loading cards...')
    model.fighting = true
    $http.get('/api/cards').then(function(response){
      model.cards = response.data
      updateRowsWithHexaValue()
    })
  }

  function updateRowsWithHexaValue(){
    var binaryValue = UTIL.leftPad(parseInt(model.hexaValue, 16).toString(2), 32)
    model.binaryValue = binaryValue
    for (var i = 1; i <= 32; i++) {
      model.choices[i] = binaryValue.charAt(32 - i) == "1" ? true : false
    }
  }

  function delay(amount, callback){
    model.loading = true
    $timeout(function(){
      model.loading = false
      callback()
    }, amount)
  }

  function l(message){
    console.log("[FOBIT] " + message)
  }

}])
