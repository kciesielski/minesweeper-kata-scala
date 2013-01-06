
import org.json4s.{DefaultFormats, Formats}
import org.minesweeperkata._
import common.SafeInt
import org.scalatra.databinding.JacksonJsonParsing
import org.scalatra.json.{JValueResult, JacksonJsonSupport}
import org.scalatra.ScalatraServlet

class MinesweeperServlet(val levelRandomizer: LevelRandomizer = new LevelRandomizer(), val gameStateDao: GameStateDao = InMemoryGameStateDao)
  extends ScalatraServlet with JacksonJsonParsing with JacksonJsonSupport with JValueResult {

  protected implicit val jsonFormats: Formats = DefaultFormats
  protected val applicationDescription = "desc"

  before() {
    contentType = formats("json")
  }

  private def extractPos(xParam: String, yParam: String): Pos = {
    val x = SafeInt(xParam).get
    val y = SafeInt(yParam).get
    Pos(x, y)
  }

  get("/tile/:x/:y") {
    val pos = extractPos(params("x"), params("y"))
    showTile(pos, gameStateDao)
  }

  put("/step") {
    val pos = parsedBody.extract[Pos]
    stepOnTile(pos, gameStateDao)
  }

  post("/randomLevel") {

  }

  get("/status") {
    gameStateDao.getState.describe
  }

}