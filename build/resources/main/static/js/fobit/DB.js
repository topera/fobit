function Db(){

    this.clear = function() {
      localStorage.removeItem("level")
      this.clearScores()
    }

    this.clearScores = function() {
      localStorage.removeItem("scores")
    }

    this.addLevel = function(level) {
      localStorage.level = level
    }

    this.getLevel = function(level) {
      return localStorage.level
    }

    this.addScore = function(combination, score) {
      var scoresStr = localStorage.scores
      var scores = scoresStr ? JSON.parse(scoresStr) : []

      if (!hasCombination(scores, combination)) {
        scores.push({"combination": combination, "score": score})
      }

      localStorage.scores = JSON.stringify(sortScores(scores))
    }

    this.getScores = function() {
      return localStorage.scores ? JSON.parse(localStorage.scores) : []
    }

    function hasCombination(scores, combination){
      return scores.findIndex(x => x.combination === combination) > -1
    }

    function sortScores(scores){
      scores.sort(function(a, b){
        return (a.score > b.score) ? -1 : 1
      })
      return scores
    }

}

window.DB = new Db();