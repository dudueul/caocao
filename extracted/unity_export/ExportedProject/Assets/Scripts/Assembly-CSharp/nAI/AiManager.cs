using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;

namespace nAI
{
	public static class AiManager
	{
		private static List<Ai> ms_processing;

		private static Ai ms_running;

		private static Ai ms_prev;

		public static bool useItem;

		public static bool log;

		public static void ResetOnTurnStart()
		{
		}

		public static void Reset()
		{
		}

		private static Ai Front()
		{
			return null;
		}

		private static int compare1(Ai x, Ai y)
		{
			return 0;
		}

		private static int compare2(Ai x, Ai y)
		{
			return 0;
		}

		private static void BuildUnitList(List<UnitData> aiUnits)
		{
		}

		[DebuggerHidden]
		public static IEnumerator Process(List<UnitData> aiUnits)
		{
			return null;
		}

		public static void ResetUnitAi(Ai unitAi, EnumData.UnitActionChangeReason reason)
		{
		}

		public static void OnUnitAiComplete()
		{
		}

		public static bool Yield(Ai yielder, Ai yieldee)
		{
			return false;
		}

		public static bool Run(Ai ai)
		{
			return false;
		}
	}
}
