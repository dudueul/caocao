using System;
using System.Collections.Generic;
using LitJson;
using Multiplay;
using UnityEngine;

namespace BattlePlay
{
	[Serializable]
	public class BattleUser : ITransmittable
	{
		public ulong uid;

		public string nationName;

		public int level;

		public int mmr;

		public int point;

		public int tierGrade;

		public int faceUnitID;

		public int skinItemID;

		public EnumData.CampLocation slot;

		private Step m_step;

		public EnumData.MultiplayBattleResult result;

		public EnumData.MultiplayUserState multiplayUserState;

		public int memberCnt;

		private Tier m_tier;

		private List<BattleUnit> m_pickedUnits;

		private List<UserUnit> m_pickedUserUnits;

		private List<UserUnit> m_battleUnits;

		private List<int> m_bannedUnitIds;

		private List<BattleUnit> m_queueUnits;

		private BattleUnit m_supportUnit;

		private BattleFormationData m_formation;

		private List<BattleTech> m_techs;

		private List<BattleUnitRelation> m_unitRelations;

		private int[] m_order;

		public bool readyToBattle;

		public bool startBattle;

		public int lastControlTurn;

		private string m_chattingRoomId;

		public FairRule fairRule;

		public Step step
		{
			get
			{
				return Step.Match;
			}
			set
			{
			}
		}

		public List<BattleUnit> pickedUnits
		{
			get
			{
				return null;
			}
		}

		public List<UserUnit> pickedUserUnits
		{
			get
			{
				return null;
			}
		}

		public List<UserUnit> battleUnits
		{
			get
			{
				return null;
			}
		}

		public List<int> bannedUnitIds
		{
			get
			{
				return null;
			}
		}

		public List<BattleUnit> queueUnits
		{
			get
			{
				return null;
			}
		}

		public BattleUnit supportUnit
		{
			get
			{
				return null;
			}
		}

		public BattleFormationData formation
		{
			get
			{
				return null;
			}
		}

		public List<BattleTech> techs
		{
			get
			{
				return null;
			}
		}

		public List<BattleUnitRelation> unitRelations
		{
			get
			{
				return null;
			}
		}

		public int[] order
		{
			get
			{
				return null;
			}
		}

		public string chattingRoomId
		{
			get
			{
				return null;
			}
		}

		public BattleUser(UserInfo userInfo, int point, int tierGrade)
		{
		}

		public BattleUser(UserInfo userInfo)
		{
		}

		public BattleUser(JsonData jsonData)
		{
		}

		public Texture getTierTexture()
		{
			return null;
		}

		public void SetDeckUnit(UserUnitGroup unitGroup)
		{
		}

		public int? GetEmptyOrder()
		{
			return null;
		}

		public BattleUnit GetBattleUnit(int unitId)
		{
			return null;
		}

		public List<UnitData> GetUnitDatas()
		{
			return null;
		}

		public UserUnit GetUserUnit(int unitId)
		{
			return null;
		}

		public UserUnit GetUserUnitBySrl(ulong srl)
		{
			return null;
		}

		public EnumData.Brawl_Unit_State GetBrawlUnitState(int unitId)
		{
			return EnumData.Brawl_Unit_State.Fight;
		}

		public bool IsBrawlSupport(int unitId)
		{
			return false;
		}

		public bool WillAutoTag()
		{
			return false;
		}

		public void UnitTag(int fightUnitId, int queueUnitId)
		{
		}

		private void SwapOrder(int srcUnitId, int dstUnitId)
		{
		}

		public int GetQueueUnitCount()
		{
			return 0;
		}

		private void Init()
		{
		}

		private void SetBrawlUnitList()
		{
		}

		private void ClearPickUnits()
		{
		}

		public override void Serialize(JsonWriter writer)
		{
		}

		protected override void DeserializeImpl(JsonData jsonData)
		{
		}

		public void SerializePickStep(JsonWriter writer)
		{
		}

		public void SerializePickedUnits(JsonWriter writer)
		{
		}

		public void DeserializePickedUnits(JsonData jsonData)
		{
		}

		public void SerializeBannedUnits(JsonWriter writer)
		{
		}

		public void DeserializeBannedUnits(JsonData jsonData)
		{
		}

		public void SerializeFormationStep(JsonWriter writer)
		{
		}

		private void SerializeFormation(JsonWriter writer)
		{
		}

		public void DeserializeFormation(JsonData jsonData)
		{
		}

		public void SerializeOrder(JsonWriter writer)
		{
		}

		public void DeserializeOrder(JsonData jsonData)
		{
		}

		public void SerializeReadyToBattle(JsonWriter writer)
		{
		}

		public void DeserializeReadyToBattle(JsonData jsonData)
		{
		}

		public void SerializeStartBattle(JsonWriter writer)
		{
		}

		public void DeserializeStartBattle(JsonData jsonData)
		{
		}

		public bool IsTechEnabled(int id)
		{
			return false;
		}

		public BattleTech GetTech(int id)
		{
			return null;
		}

		public void SerializeTech(JsonWriter writer)
		{
		}

		public void DeserializeTech(JsonData jsonData)
		{
		}

		public void SerializeUnitRelations(JsonWriter writer)
		{
		}

		public void DeserializeUnitRelations(JsonData jsonData)
		{
		}

		public void SerializeLastControlTurn(JsonWriter writer)
		{
		}
	}
}
