using System.Collections.Generic;

namespace nAI
{
	public static class Util
	{
		private static int ms_aiIDCorrect;

		public static void OnNewBattle()
		{
		}

		public static void OnNewTurn()
		{
		}

		public static void OnNewAction()
		{
		}

		public static List<IntVector2> GetAttackablePositions(UnitData actor, UnitData target)
		{
			return null;
		}

		public static int CorrectId(int id)
		{
			return 0;
		}

		public static bool IsBlocking(TileNode tile, UnitData unit)
		{
			return false;
		}
	}
}
