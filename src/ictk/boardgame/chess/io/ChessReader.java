/*
 *  ICTK - Internet Chess ToolKit
 *  More information is available at http://ictk.sourceforge.net
 *  Copyright (C) 2002 J. Varsoke <jvarsoke@ghostmanonfirst.com>
 *  All rights reserved.
 *
 *  $Id: ChessReader.java,v 1.1.1.1 2003/03/24 22:38:12 jvarsoke Exp $
 *
 *  This file is part of ICTK.
 *
 *  ICTK is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  ICTK is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with ICTK; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */


package ictk.boardgame.chess.io;

import ictk.boardgame.io.GameReader;
import ictk.boardgame.Game;
import ictk.boardgame.History;
import ictk.boardgame.Board;
import ictk.boardgame.GameInfo;
import ictk.boardgame.IllegalMoveException;

import ictk.boardgame.chess.*;
import java.io.IOException;
import java.io.Reader;

/* ChessGameReader ********************************************************/
/**
 * ChessGameReader is an abstract class that parses input streams and
 * produces Game objects.
 */
public abstract class ChessReader extends GameReader {

   public ChessReader (Reader _ir) {
      super(_ir);
   }

   public abstract Game getGame();
}
