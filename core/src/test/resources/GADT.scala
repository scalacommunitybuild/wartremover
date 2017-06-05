sealed trait ConsoleIO[A]
object ConsoleIO {
  final case class PrintLn(message: String) extends ConsoleIO[Unit]
}

object GADTMain {
  def consoleIO =
    new Object {
      import ConsoleIO._

      def apply[A](c: ConsoleIO[A]) = c match {
        case PrintLn(message) => println(message)
      }
    }
}
