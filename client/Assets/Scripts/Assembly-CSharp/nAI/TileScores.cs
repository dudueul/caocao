namespace nAI
{
	public struct TileScores
	{
		public IntVector2 grid;

		public UnitData unitData;

		private float? m_adjustedPoint;

		private float? m_safeDistancePoint;

		private float? m_ownDistancePoint;

		private float? m_tileBonusPoint;

		private float? m_tileJobPoint;

		private float? m_movePoint;

		private float? m_threatDistancePoint;

		private float? m_tileGoalPoint;

		private float? m_tilePreferencePoint;

		public float adjustedPoint
		{
			get
			{
				return 0f;
			}
			set
			{
			}
		}

		public float safeDistancePoint
		{
			get
			{
				return 0f;
			}
		}

		public float ownDistancePoint
		{
			get
			{
				return 0f;
			}
		}

		public float tileBonusPoint
		{
			get
			{
				return 0f;
			}
		}

		public float tileJobPoint
		{
			get
			{
				return 0f;
			}
		}

		public float movePoint
		{
			get
			{
				return 0f;
			}
		}

		public float threatDistancePoint
		{
			get
			{
				return 0f;
			}
		}

		public float tileGoalPoint
		{
			get
			{
				return 0f;
			}
		}

		public float tilePreferencePoint
		{
			get
			{
				return 0f;
			}
		}
	}
}
