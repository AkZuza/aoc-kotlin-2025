fun main() {
  val rotations = readInput("Day01.txt")
  var currentRotation = 50
  var passcode = 0;

  for (rotation in rotations) {
    val direction = if (rotation[0] == 'L') -1 else 1
    val distance = rotation.substring(1).toIntOrNull() ?: 0

    for ( i in 1..distance) {
      currentRotation += direction

      if(currentRotation == -1) {
        currentRotation = 99
      } else if (currentRotation >= 100) {
        currentRotation = 0
        passcode += 1
      } else if (currentRotation == 0) {
        passcode += 1
      }
    }
  }

  println("Passcode is $passcode")
}
