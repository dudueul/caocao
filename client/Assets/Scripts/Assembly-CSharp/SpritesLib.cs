using System;
using System.Collections.Generic;
using UnityEngine;

public class SpritesLib
{
	public enum UnitMotionType
	{
		attack = 0,
		move = 1,
		spc = 2
	}

	public enum BattleSpriteType
	{
		main = 0,
		dye = 1
	}

	private static Dictionary<string, Sprite> m_path2sprite;

	private static Dictionary<string, Texture> m_path2texture;

	private static Dictionary<string, Material> m_path2material;

	private const int targetFocusMax = 9;

	private static string ms_facePathFormat_builtin;

	private static string ms_townBgPathFormat_builtin;

	private static string ms_battleMapPathFormat_builtin;

	private static string ms_battleMapSubstancePathFormat_builtin;

	private static string ms_eventMapPathFormat_builtin;

	private static string duelMapFileNameFormat;

	private static string duelMapPathFormat_builtin;

	private static readonly string __2dtkPrefabNameFormatOnResources__;

	private static readonly string __2dtkMainTexFormatOnResources__;

	private static readonly string __2dtkGridSpriteFormat;

	private static readonly string __2dtkPrefabNameDefault;

	private static readonly string __2dtkEventPrefabNameFormatOnResources__;

	private static readonly string __2dtkEventMainTexFormatOnResources__;

	private static readonly string __2dtkEventPrefabNameDefault;

	private static readonly string __2dtkForceSpritePrefabNameFormatOnResources__;

	private static readonly string __2dtkForceAnimationPrefabNameFormatOnResources__;

	private static readonly string __2dtkForceMainTexFormatOnResources__;

	private static readonly string __2dtkForceMaterialFormatOnResources__;

	private static readonly string __GuildWarMapObject2dtkForceSpritePrefabNameFormatOnResources__;

	private static readonly string __GuildWarMapObject2dtkForceAnimationPrefabNameFormatOnResources__;

	private static readonly string __GuildWarMapObject2dtkForceMainTexFormatFormatOnResources__;

	public static Sprite m_sprite_bg_box
	{
		get
		{
			return null;
		}
	}

	public static Sprite m_sprite_selection_w
	{
		get
		{
			return null;
		}
	}

	public static Sprite m_sprite_selection_b
	{
		get
		{
			return null;
		}
	}

	public static Sprite m_sprite_selection_g
	{
		get
		{
			return null;
		}
	}

	public static Sprite m_sprite_selection_r
	{
		get
		{
			return null;
		}
	}

	public static Sprite m_sprite_selection_y
	{
		get
		{
			return null;
		}
	}

	public static Sprite m_sprite_selection_y2
	{
		get
		{
			return null;
		}
	}

	public static Sprite m_sprite_selection_y_enemy
	{
		get
		{
			return null;
		}
	}

	public static Sprite m_sprite_frame_range
	{
		get
		{
			return null;
		}
	}

	public static Sprite m_sprite_target_focusing_enemy
	{
		get
		{
			return null;
		}
	}

	public static Sprite m_sprite_target_focusing_enemy_both
	{
		get
		{
			return null;
		}
	}

	public static Sprite sprite_tile_destination
	{
		get
		{
			return null;
		}
	}

	public static Sprite sprite_tile_alert
	{
		get
		{
			return null;
		}
	}

	public static Sprite m_sprite_cbox_ally1
	{
		get
		{
			return null;
		}
	}

	public static Sprite m_sprite_cbox_ally2
	{
		get
		{
			return null;
		}
	}

	public static Sprite m_sprite_cbox_enemy
	{
		get
		{
			return null;
		}
	}

	public static Sprite m_sprite_cbox_own
	{
		get
		{
			return null;
		}
	}

	public static Sprite m_sprite_cbox_leader
	{
		get
		{
			return null;
		}
	}

	public static Sprite m_sprite_cbox_base
	{
		get
		{
			return null;
		}
	}

	public static Sprite m_sprite_cbox_food
	{
		get
		{
			return null;
		}
	}

	public static Sprite m_sprite_cbox_silver
	{
		get
		{
			return null;
		}
	}

	public static Sprite m_sprite_cbox_boss
	{
		get
		{
			return null;
		}
	}

	public static Sprite m_sprite_cbox_elite
	{
		get
		{
			return null;
		}
	}

	public static Sprite m_sprite_stat_bar_base
	{
		get
		{
			return null;
		}
	}

	public static Sprite m_sprite_stat_bar_battle
	{
		get
		{
			return null;
		}
	}

	public static UIAtlas GetGuildTextureAtlas
	{
		get
		{
			return null;
		}
	}

	public static void Clear()
	{
	}

	public static Sprite GetSprite(string path)
	{
		return null;
	}

	public static Texture GetTexture(string path)
	{
		return null;
	}

	public static Material GetMaterial(string path)
	{
		return null;
	}

	public static Texture GetAnnihilationWarBattleMapTexture(string battleMapTextureName)
	{
		return null;
	}

	public static Texture GetAnnihilationWarLeagueTexture(string iconTextureName)
	{
		return null;
	}

	public static Texture GetThousandBattleMiniMapTexture(string miniMapTextureName)
	{
		return null;
	}

	public static Texture GetThousandGateTexture(string gateTextureName)
	{
		return null;
	}

	public static Texture GetThousandBgTexture(string bgTextureName)
	{
		return null;
	}

	public static Texture GetArenaBattleMapThumbnail(string textureName)
	{
		return null;
	}

	public static Texture GetArenaBattleMapTileTexture(string textureName)
	{
		return null;
	}

	public static Sprite m_sprite_target_focusing(int index)
	{
		return null;
	}

	public static Sprite GetMapObjectColorBox(int mapObjectId)
	{
		return null;
	}

	public static UIAtlas GetPassiveAtlas(int passiveId, int val)
	{
		return null;
	}

	public static UIAtlas GetPassiveAtlas(string atlasName)
	{
		return null;
	}

	public static UIAtlas GetConditionAtlas()
	{
		return null;
	}

	public static Texture GetCronicleTexture(string folder, string img)
	{
		return null;
	}

	public static UIAtlas GetTextureAtlas(string path)
	{
		return null;
	}

	[Obsolete]
	public static Sprite GetItemIcon(int itemId)
	{
		return null;
	}

	public static Sprite GetSkillIcon(int skillId)
	{
		return null;
	}

	public static string GetFacePathWithUnitID(int unitID)
	{
		return null;
	}

	public static string GetFacePath(int faceId)
	{
		return null;
	}

	public static string GetFacePath(int faceId, int faceType)
	{
		return null;
	}

	public static Texture GetFaceWithName(string faceName)
	{
		return null;
	}

	public static Sprite GetSkillHitRange(int skillId)
	{
		return null;
	}

	public static Sprite GetSkillEffectRange(int skillId)
	{
		return null;
	}

	public static Sprite GetJobHitRange(int jobId)
	{
		return null;
	}

	public static Sprite GetJobEffectRange(int jobId)
	{
		return null;
	}

	public static Texture GetChronicleChapterImage(int chapterNo)
	{
		return null;
	}

	public static Texture GetChronicleEndingImage(string resName)
	{
		return null;
	}

	public static Texture GetTownBGImage(int townID)
	{
		return null;
	}

	public static string GetTownBGImagePath(int townID)
	{
		return null;
	}

	public static Sprite GetBattleMapThumnail(int scenarioId)
	{
		return null;
	}

	public static Sprite GetBattleMapThumnail(string battleImage)
	{
		return null;
	}

	public static Sprite GetBattleMapImage(int mapId)
	{
		return null;
	}

	public static string GetBattleMapPath(int mapId)
	{
		return null;
	}

	public static string GetBattleMapSubstancePath(int mapId)
	{
		return null;
	}

	public static string GetEventMapPath(int mapId)
	{
		return null;
	}

	public static Sprite GetEventMapImage(int eventMapId)
	{
		return null;
	}

	public static Sprite GetDuelIntroImage(int introMapId)
	{
		return null;
	}

	public static string GetDuelBackgroundName(int duelMapId)
	{
		return null;
	}

	public static string GetDuelBackgroundPath(int duelMapId)
	{
		return null;
	}

	public static string GetDuelBackgroundPath(string fileName)
	{
		return null;
	}

	public static void SetUnitGridTexture(UITexture unitTexture, int unitID, int skinId, int jobTypeID, int jobLevel)
	{
	}

	protected static Texture GetUnitGridTextureByJobType(string prefabName, string jobTypeName, int jobLevel)
	{
		return null;
	}

	public static Texture GetUnitTexture(int unitID, int skinId, int jobTypeID, int jobLevel, out string path, FilterMode filterMode)
	{
		path = null;
		return null;
	}

	protected static Texture GetUnitTexNameByJobType(string prefabName, string jobTypeName, int jobLevel, out string path, FilterMode filterMode)
	{
		path = null;
		return null;
	}

	public static GameObject GetUnitCollection(int unitID, int skinId, int jobTypeID, int jobLevel)
	{
		return null;
	}

	protected static GameObject GetUnitCollectionByJobType(string prefabName, string jobTypeName, int jobLevel)
	{
		return null;
	}

	public static Texture GetEventUnitTexture(int unitID, out string path)
	{
		path = null;
		return null;
	}

	protected static Texture GetEventUnitTexture(string prefabName, out string path)
	{
		path = null;
		return null;
	}

	public static GameObject GetEventUnitCollection(int unitID)
	{
		return null;
	}

	protected static GameObject GetEventUnitCollection(string prefabName)
	{
		return null;
	}

	public static Sprite GetTownImage(int townId)
	{
		return null;
	}

	public static tk2dSpriteAnimation GetTownBuilding(string objName, int townGrade)
	{
		return null;
	}

	public static tk2dSpriteAnimation GetTownInterior(string objName)
	{
		return null;
	}

	public static Sprite GetEventMapEnvObjImage(string filePath)
	{
		return null;
	}

	public static Texture GetSkinItemTexture(int skinID)
	{
		return null;
	}

	public static Texture GetUserVisibleFaceUnitTexture(UserInfo userInfo)
	{
		return null;
	}

	public static Texture GetFaceTexture(UserUnit userUnit)
	{
		return null;
	}

	public static Texture GetFaceTextureByUnitIdAndSkinItemId(int unitId, int skinItemId)
	{
		return null;
	}

	public static Texture GetFaceTextureByUnitIdAndSkinId(int unitId, int skinId)
	{
		return null;
	}

	public static Texture GetFaceTextureByUnitId(int unitId)
	{
		return null;
	}

	public static Texture GetFaceTextureByHeroId(int heroId)
	{
		return null;
	}

	public static Texture GetFaceTextureBySkinItemId(int skinItemId)
	{
		return null;
	}

	public static Texture GetFaceTextureBySkinId(int skinId)
	{
		return null;
	}

	public static Texture GetFaceTextureByFaceId(int faceId, int faceType)
	{
		return null;
	}

	public static Texture GetFaceTextureByFaceId(int faceId)
	{
		return null;
	}

	public static Sprite GetNationFlagSprite(string nationCode)
	{
		return null;
	}

	public static Texture GetShopIcon(string iconName)
	{
		return null;
	}

	public static Texture GetItemTypeIcon(string iconName)
	{
		return null;
	}

	public static Texture GetItemTexture(Item item)
	{
		return null;
	}

	public static Texture GetItemTexture(string atlas, string icon)
	{
		return null;
	}

	public static Texture GetUnitTexture(string unitName)
	{
		return null;
	}

	public static Texture GetGuildMarkTexture(int index)
	{
		return null;
	}

	public static Texture GetGuildLobbyBg(string texName)
	{
		return null;
	}

	public static Texture GetRankupGiftIcon(string texName)
	{
		return null;
	}

	public static Texture GetTreeTicketIcon(string texName)
	{
		return null;
	}

	public static Texture GetRuneTexture(Rune runePd)
	{
		return null;
	}

	public static Texture GetNoItemTexture()
	{
		return null;
	}

	public static Texture GetNoRuneTexture()
	{
		return null;
	}

	public static Texture GetCompetitionBoxTexture(string icon)
	{
		return null;
	}

	public static Texture GetCompetitionBoxItemTexture(string icon)
	{
		return null;
	}

	public static Texture GetBingoTexture(string icon)
	{
		return null;
	}

	public static GameObject GetForceSpriteCollection(string prefabName, string sizeName)
	{
		return null;
	}

	public static GameObject GetForceAnimation(string prefabName, string sizeName)
	{
		return null;
	}

	public static Texture GetForceTexture(string prefabName, string sizeName, out string path)
	{
		path = null;
		return null;
	}

	public static Material GetForceMaterial(string prefabName, string sizeName, out string path)
	{
		path = null;
		return null;
	}

	public static GameObject GetGuildWarMapSpriteCollection(string ObjectName, string ObjectIndex)
	{
		return null;
	}

	public static GameObject GetGuildWarMapAnimation(string ObjectName, string ObjectIndex)
	{
		return null;
	}

	public static Texture GetGuildWarMapTexture(string prefabName, string sizeName, out string path)
	{
		path = null;
		return null;
	}

	public static Texture GetBaseStatIcon(EnumData.StatType statType)
	{
		return null;
	}

	public static Texture GetWeaknessIcon(EnumData.WeaknessType weakness, bool isRevealed)
	{
		return null;
	}

	public static Texture GetGuildWarTargetEffectTexture(int effectArea)
	{
		return null;
	}

	public static Texture GetGuildWarTargetRangeTexture(int targetRange)
	{
		return null;
	}

	public static UIAtlas GetGuildWarPassiveAtlas(string atlasName)
	{
		return null;
	}

	public static UIAtlas GetGuildWarSkillAtlas(string atlasName)
	{
		return null;
	}
}
