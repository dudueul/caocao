using System;
using System.Collections.Generic;
using Caocao;
using LitJson;
using Multiplay;

namespace BattlePlay
{
	[Serializable]
	public class BattlePlayData : ITransmittable
	{
		private int m_battleId;

		private string m_battleKey;

		private Dictionary<ulong, BattleUser> m_battleUserDic;

		private List<BattleUser> m_battleUserList;

		private bool m_friendly;

		private ulong m_hostUid;

		private BattleUser m_host;

		private BattleUser m_opponent;

		private List<BattleControl> m_controls;

		private int[] m_randomList;

		private int[] m_turnTime;

		private List<BattleSticker> m_stickers;

		private FairRule m_fairRule;

		public int battleId
		{
			get
			{
				return 0;
			}
		}

		public string battleKey
		{
			get
			{
				return null;
			}
		}

		public Dictionary<ulong, BattleUser> battleUserDic
		{
			get
			{
				return null;
			}
		}

		public List<BattleUser> battleUserList
		{
			get
			{
				return null;
			}
		}

		public bool friendly
		{
			get
			{
				return false;
			}
		}

		public BattleUser host
		{
			get
			{
				return null;
			}
		}

		public BattleUser opponent
		{
			get
			{
				return null;
			}
		}

		// C# has no syntax for parameterized property 'Item'.
		public BattleUser get_Item(ulong uid)
		{
			return null;
		}

		public List<BattleControl> controls
		{
			get
			{
				return null;
			}
		}

		public BattleControl battleControl
		{
			get
			{
				return null;
			}
		}

		public BattleControl lastAppliedControl
		{
			get
			{
				return null;
			}
		}

		public BattleControl lastControl
		{
			get
			{
				return null;
			}
		}

		public bool didCompleteProcessControls
		{
			get
			{
				return false;
			}
		}

		private int m_controlIdx;   // [RE] rebuilt: manager advances this index

		public int controlIdx
		{
			get
			{
				return m_controlIdx;
			}
			set
			{
				m_controlIdx = value;
			}
		}

		public List<BattleSticker> skilters
		{
			get
			{
				return null;
			}
		}

		public BattleSticker sticker
		{
			get
			{
				return null;
			}
		}

		public int stickerIdx
		{
			get
			{
				return 0;
			}
		}

		public int[] turnTime
		{
			get
			{
				return null;
			}
		}

		public FairRule fairRule
		{
			get
			{
				return null;
			}
		}

		public BattlePlayData(int bid, UserInfo hostUser)
		{
		}

		public BattlePlayData(ulong hostUid, JsonData battleData)
		{
		}

		public void Init(int bid, UserInfo hostUser)
		{
		}

		private void Init()
		{
		}

		public BattleControl GenerateControl(ulong uid, ControlType type, int randomIdx, ControlFlow.statusType status, UnitData controlUnit, UnitData targetUnit, int tileIdx, int skillId, int itemId, IntVector2 mouseGridPos, int turnIdx, int remainedSec)
		{
			return null;
		}

		public BattleControl GenerateUserSpellControl(int randomIdx, ControlFlow.statusType status, List<UnitData> targetUnits, int tileIdx, int userSpellId, IntVector2 mouseGridPos, int turnIdx, int remainedSec)
		{
			return null;
		}

		public void AddControl(BattleControl battleControl)
		{
		}

		public BattleSticker GenerateSticker(ulong uid, int stickerId, int turnIdx, int remainedSec)
		{
			return null;
		}

		public void AddSticker(BattleSticker battleSticker)
		{
		}

		protected override void DeserializeImpl(JsonData jsonData)
		{
		}

		public void SetMMR(ulong uid, int mmr)
		{
		}

		public void SetStep(ulong uid, Step step)
		{
		}

		public void SerializeControlInfo(JsonData controlInfo)
		{
		}

		private void DeserializeBattleUsers(JsonData jsonData)
		{
		}

		private void DeserializeControls(JsonData jsonData)
		{
		}

		private void DeserializeStickers(JsonData jsonData)
		{
		}

		private void DeserializeRandom(JsonData jsonData)
		{
		}

		private int[] DeserializeNumList(JsonData jsonData, string attrName)
		{
			return null;
		}

		public void SetTurnTime(int[] turnTime)
		{
		}

		public UnitSide SlotToSide(EnumData.CampLocation slot)
		{
			return UnitSide.OWN;
		}

		public EnumData.CampLocation SideToSlot(UnitSide side)
		{
			return EnumData.CampLocation.Left;
		}

		public int GetLastRandomIndex()
		{
			return 0;
		}

		public void SerializeLastControlTurn(JsonWriter writer)
		{
		}

		public void DeserializeLastControlTurn(JsonData jsonData)
		{
		}

		public BattleUser GetWinner()
		{
			return null;
		}

		public BattleUser GetRetreater()
		{
			return null;
		}

		public void DeserializeFriendlyMatching(JsonData jsonData)
		{
		}

		private void DeserializeFairRule(JsonData jsonData)
		{
		}
	}
}
