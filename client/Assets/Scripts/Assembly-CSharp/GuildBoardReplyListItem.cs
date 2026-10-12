using LitJson;
using UnityEngine;

public class GuildBoardReplyListItem : MonoBehaviour
{
	[SerializeField]
	private Transform m_guildJobParent;

	[SerializeField]
	private UILabel m_nationName;

	[SerializeField]
	private UILabel m_regDate;

	[SerializeField]
	private UILabel m_content;

	[SerializeField]
	private GameObject m_deleteBtn;

	[SerializeField]
	private GameObject m_emoticon;

	[SerializeField]
	private UITexture[] m_emoticonList;

	private GuildBoardReplyData m_replyData;

	public void SetReplyData(GuildBoardReplyData replyData)
	{
	}

	private void UpdateUI()
	{
	}

	private void SetContent()
	{
	}

	public void OnUpdateUserInfo(UserInfo userInfo)
	{
	}

	public void OnClickDelete()
	{
	}

	private void OnDeleteReply(JsonData response)
	{
	}
}
