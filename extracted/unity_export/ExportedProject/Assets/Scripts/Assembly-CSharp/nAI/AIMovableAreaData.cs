using System.Collections.Generic;

namespace nAI
{
	public class AIMovableAreaData
	{
		private EnumData.aiMovableAreaType type;

		private List<IntRect> movableRects;

		private List<IntVector2> movableGrids;

		private int bonusPoint;

		public AIMovableAreaData(EnumData.aiMovableAreaType areaType, int score)
		{
		}

		public AIMovableAreaData(EnumData.aiMovableAreaType areaType, int score, List<IntRect> rects)
		{
		}

		public AIMovableAreaData(EnumData.aiMovableAreaType areaType, int score, List<IntVector2> grids)
		{
		}

		public int GetScore(IntVector2 gridPos, IntVector2 pivotPos)
		{
			return 0;
		}
	}
}
