package lam.design.pattern.chainofresponsibility.rh;

import lam.design.pattern.chainofresponsibility.rh.AbstractRequest.Level;
import lam.log.Console;

/**
* <p>
* TODO
* </p>
* @author linanmiao
* @date 2017年4月27日
* @versio 1.0
*/
public class HandlerL02 extends AbstractHandler{

	public HandlerL02() {
		super(Level.L02);
	}

	@Override
	public void handle(AbstractRequest request) {
		Console.println(getClass().getSimpleName() + " handle " + request);
	}

}
