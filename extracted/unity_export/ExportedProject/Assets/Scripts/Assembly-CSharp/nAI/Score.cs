using System.Collections.Generic;
using Caocao;

namespace nAI
{
	public static class Score
	{
		private static PermanentData m_pd;

		private static List<UnitData> ownUnits;

		private static List<List<UnitData>> enemyUnitsGroup;

		private static EnumData.ItemEffect[] healEffect;

		private static EnumData.ItemEffect[] healEffectPct;

		private static PermanentData pd
		{
			get
			{
				return null;
			}
		}

		public static void Prepare(UnitSide actorUnitSide)
		{
		}

		public static void Reset()
		{
		}

		public static float GetAtkDamagePoint(Action action)
		{
			return 0f;
		}

		public static float GetSkillDamagePoint(Action action)
		{
			return 0f;
		}

		public static float GetItemDamagePoint(Action action)
		{
			return 0f;
		}

		public static float GetDefencePoint(Action action, IntVector2 gridPos)
		{
			return 0f;
		}

		public static float GetSafeDistancePoint(UnitData actor, IntVector2 gridPos)
		{
			return 0f;
		}

		public static float GetThreatDistancePoint(UnitData actor, IntVector2 gridPos)
		{
			return 0f;
		}

		public static float GetOwnDistancePoint(UnitData actor, IntVector2 gridPos)
		{
			return 0f;
		}

		public static float GetMovePoint(UnitData actor, IntVector2 gridPos)
		{
			return 0f;
		}

		public static float GetMPUsePoint(Action action)
		{
			return 0f;
		}

		public static float GetTileJobPoint(UnitData actor, IntVector2 gridPos)
		{
			return 0f;
		}

		public static float GetTileBonusPoint(UnitData actor, IntVector2 gridPos)
		{
			return 0f;
		}

		public static float GetNonDamageSkillAccuracy(Action action)
		{
			return 0f;
		}

		private static float GetDamageMultiTargetPoint(Action action)
		{
			return 0f;
		}

		private static float GetDamageSkillMultiTargetPoint(Action action)
		{
			return 0f;
		}

		public static float GetNonDamageMultiTargetPoint(Action action)
		{
			return 0f;
		}

		private static float GetHPHealMultiTargetPoint(Action action)
		{
			return 0f;
		}

		private static float GetMPHealMultiTargetPoint(Action action)
		{
			return 0f;
		}

		public static float GetRecoverPoint(Action action)
		{
			return 0f;
		}

		public static float GetHPHealPoint(Action action)
		{
			return 0f;
		}

		public static float GetMPHealPoint(Action action)
		{
			return 0f;
		}

		public static float GetTargetValuePoint(Action action)
		{
			return 0f;
		}

		public static float GetTargetAdditionalScore(Action action)
		{
			return 0f;
		}

		public static float GetItemUsePoint(Action action)
		{
			return 0f;
		}

		public static float GetItemHealPoint(Action action, EnumData.UnitResType resType)
		{
			return 0f;
		}

		public static float GetItemRecoverPoint(Action action)
		{
			return 0f;
		}

		private static float GetPDPA(UnitData actor, UnitData target)
		{
			return 0f;
		}

		private static float GetAtkDamage(Action action)
		{
			return 0f;
		}

		private static float GetSDPA(Action action)
		{
			return 0f;
		}

		private static float GetSkillDamage(Action action)
		{
			return 0f;
		}

		private static float GetCounterDamage(Action action, IntVector2 gridPos)
		{
			return 0f;
		}

		private static int CalcSafeDistance(UnitData actor, List<TileNode> path, IntVector2 gridPos)
		{
			return 0;
		}

		private static int CalcCostToGrid(UnitData actor, List<TileNode> path, IntVector2 gridPos)
		{
			return 0;
		}

		private static int GetAtkEffectedEnemyCount(Action action)
		{
			return 0;
		}

		private static float GetSkillHPHealAmount(Action action)
		{
			return 0f;
		}

		private static float GetSkillMPHealAmount(Action action)
		{
			return 0f;
		}

		private static float GetItemHealAmount(Action action)
		{
			return 0f;
		}

		private static float GetTileHealAmount(Action action, TileNode tileNode)
		{
			return 0f;
		}

		public static float GetSkillPoint(Action action)
		{
			return 0f;
		}

		public static bool WillAllowUseSkill(UnitData actor, UnitData target, EnumData.Skills skillID)
		{
			return false;
		}

		public static float GetMoveSkillPoint(Action action)
		{
			return 0f;
		}

		public static bool WillAllowUseItem(UnitData actor, UnitData target, int itemID)
		{
			return false;
		}

		public static float GetTargetSpActionPoint(Action action)
		{
			return 0f;
		}

		public static float GetActorSpActionPoint(Action action)
		{
			return 0f;
		}

		public static float GetAiTileGoalPoint(UnitData actor, IntVector2 gridPos)
		{
			return 0f;
		}

		public static float GetAiActionGoalPoint(Action action, IntVector2 gridPos)
		{
			return 0f;
		}

		public static float GetAiTilePreferencePoint(UnitData actor, IntVector2 gridPos)
		{
			return 0f;
		}
	}
}
