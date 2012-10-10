/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data.dao.special;

import data.SingleGame;
import data.dao.general.SingleGameDAOImpl;

public class LevelDAO extends SingleGameDAOImpl<SingleGame> {

	public LevelDAO() {
		super(SingleGame.class);
	}

}
