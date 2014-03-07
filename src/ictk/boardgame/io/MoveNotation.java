/*
 *  ICTK - Internet Chess ToolKit
 *  More information is available at http://ictk.sourceforge.net
 *  Copyright (C) 2002 J. Varsoke <jvarsoke@ghostmanonfirst.com>
 *  All rights reserved.
 *
 *  $Id: MoveNotation.java,v 1.1.1.1 2003/03/24 22:38:06 jvarsoke Exp $
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

package ictk.boardgame.io;

import ictk.boardgame.*;

/* MoveNotation *************************************************************/
/** An interface for converting between Moves and Strings.
 */
public interface MoveNotation {

   /** convert a String into a Move.  The Board is necessary because often
    *  moves are made in a short-hand that is relative to the board.
    */
   public abstract Move stringToMove (Board b, String s)
          throws AmbiguousMoveException, IllegalMoveException;

   /** convert a Move into a String notation 
    */
   public abstract String moveToString (Move m);

   /** convert a String into a Result object
    */
   public abstract Result stringToResult (String s);

   /** convert a Result into a String.
    */
   public abstract String resultToString (Result result);


}
