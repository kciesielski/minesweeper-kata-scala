
import org.json4s.{DefaultFormats, Formats}
import org.minesweeperkata._
import common.SafeInt
import org.scalatra.databinding.JacksonJsonParsing
import org.scalatra.json.{JValueResult, JacksonJsonSupport}
import org.scalatra.ScalatraServlet
import rest.NewGame

class MinesweeperServlet(val levelRandomizer: LevelRandomizer = new LevelRandomizer(), val gameStateDao: GameStateDao = InMemoryGameStateDao)
  extends ScalatraServlet with JacksonJsonParsing with JacksonJsonSupport with JValueResult {

  protected implicit val jsonFormats: Formats = DefaultFormats
  protected val applicationDescription = "desc"

  before() {
    contentType = formats("json")
  }

  put("/step") {
    val pos = parsedBody.extract[Pos]
    val tile: TileValue = showTile(pos, gameStateDao)
    stepOnTile(pos, gameStateDao)
    tile
  }

  post("/randomLevel") {
    val params = parsedBody.extract[NewGame]
    randomize(params.width, params.height, params.mineCount, levelRandomizer, gameStateDao)
  }

}