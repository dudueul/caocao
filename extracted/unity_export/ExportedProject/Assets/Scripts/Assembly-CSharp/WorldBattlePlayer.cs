using BattlePlay;
using UnityEngine;

public class WorldBattlePlayer : MonoBehaviour
{
	public UITexture faceUiTexture;

	public UILabelLocalized forceIntLabel;

	public UILabelLocalized guildNameLabel;

	public UISprite guildIconSprite;

	public UISprite leaderSkill1Sprite;

	public UISprite leaderSkill2Sprite;

	public UILabelLocalized leaderSkillNoneLabel;

	public UILabelLocalized nationName;

	public Transform instNationFlag;

	public UILabelLocalized rankIntLabel;

	public UISprite pvpRankingIcon;

	public UILabelLocalized tierGradeLabel;

	public UILabelLocalized tierValueLabel;

	public UISprite tierIconSprite;

	public UITexture[] faceUiTextureList;

	public UITexture competitionTierIconTexture;

	public void UpdatePlayerInfo(EnumData.BattleMode battleMode, IUserDataBase userInfo)
	{
	}

	public void UpdatePlayerInfo(EnumData.BattleMode battleMode, BattleUser battleUser, bool hideNationGuildInfo)
	{
	}

	private void UpdateGuildInfo(EnumData.BattleMode battleMode, ulong uid, bool hideNationGuildInfo)
	{
	}

	private bool IsShowFaceUI(EnumData.BattleMode battleMode, BattleUser battleUser, int unitId)
	{
		return false;
	}
}
