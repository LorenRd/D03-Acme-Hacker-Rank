
package any;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.PositionService;
import controllers.AbstractController;
import domain.Position;

@Controller
@RequestMapping("/position")
public class PositionController extends AbstractController {

	// Services

	@Autowired
	private PositionService	positionService;


	// List

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Position> positions;

		positions = this.positionService.findAll();

		result = new ModelAndView("position/list");
		result.addObject("positions", positions);
		result.addObject("requestURI", "position/list.do");

		return result;
	}

	// Display

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int positionId) {
		// Inicializa resultado
		ModelAndView result;
		Position position;

		// Busca en el repositorio
		position = this.positionService.findOne(positionId);
		Assert.notNull(position);

		// Crea y a�ade objetos a la vista
		result = new ModelAndView("position/display");
		result.addObject("requestURI", "position/display.do");
		result.addObject("position", position);

		// Env�a la vista
		return result;
	}
}
