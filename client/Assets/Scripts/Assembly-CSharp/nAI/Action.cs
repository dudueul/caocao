using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.Text;

namespace nAI
{
	public class Action : IAction
	{
		public enum ActionType
		{
			Move = 0,
			Attack = 1,
			Skill = 2,
			Item = 3
		}

		private float? m_actionScore;

		public float randomScore;

		private static StringBuilder ms_sb;

		public List<TileNode> tiles;

		private static int ms_srl;

		private int _srl;

		public float actionScore
		{
			get
			{
				return 0f;
			}
			set
			{
			}
		}

		public float score
		{
			get
			{
				return 0f;
			}
		}

		public ActionType type { get; set; }

		public EnumData.Skills skillID { get; set; }

		public int itemID { get; set; }

		public UnitData actor { get; set; }

		public UnitData target { get; set; }

		public TileNode bestTile
		{
			get
			{
				return null;
			}
		}

		public IntVector2 teleportGridPos { get; set; }

		public virtual int hashCode
		{
			get
			{
				return 0;
			}
		}

		public override string ToString()
		{
			return null;
		}

		public Status GetStatus()
		{
			return Status.DestUnavailable;
		}

		private bool CanMoveToDest()
		{
			return false;
		}

		public List<TileNode> GetBestTiles(int Count)
		{
			return null;
		}

		[DebuggerHidden]
		public IEnumerator Process()
		{
			return null;
		}

		[DebuggerHidden]
		public IEnumerator ProcessPvpAI()
		{
			return null;
		}

		[DebuggerHidden]
		private IEnumerator ProcessMove()
		{
			return null;
		}

		[DebuggerHidden]
		private IEnumerator ProcessAttack()
		{
			return null;
		}

		[DebuggerHidden]
		private IEnumerator ProcessSkill()
		{
			return null;
		}

		[DebuggerHidden]
		private IEnumerator ProcessItem()
		{
			return null;
		}

		private float GetScore()
		{
			return 0f;
		}

		private float GetAttackScore()
		{
			return 0f;
		}

		private float GetSkillScore()
		{
			return 0f;
		}

		private float GetDamageSkillScore()
		{
			return 0f;
		}

		private float GetNoDamageSkillScore()
		{
			return 0f;
		}

		private float GetRecoverSkillScore()
		{
			return 0f;
		}

		private float GetHPHealSkillScore()
		{
			return 0f;
		}

		private float GetMPHealSkillScore()
		{
			return 0f;
		}

		private float GetMoveSkillScore()
		{
			return 0f;
		}

		private float GetItemScore()
		{
			return 0f;
		}

		private float GetHealItemScore(EnumData.UnitResType resType)
		{
			return 0f;
		}

		private float GetRecoverItemScore()
		{
			return 0f;
		}

		private float GetDamageItemScore()
		{
			return 0f;
		}

		private float GetMoveScore()
		{
			return 0f;
		}
	}
}
