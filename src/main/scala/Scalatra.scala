import javax.servlet.ServletContext
import org.scalatra.LifeCycle

class Scalatra extends LifeCycle {

  val Prefix = "/rest"

  override def init(context: ServletContext) {

    context.mount(new MinesweeperServlet, Prefix + "/game")
  }

}