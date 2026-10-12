using UnityEngine;

public class BattleCommunicationUI : MonoBehaviour
{
	private string m_key;

	private bool m_stickerBlock;

	public const string playerKey = "player";

	public const string enemyKey = "enemy";

	private readonly Color32 playerColor;

	private readonly Color32 enemyColor;

	[SerializeField]
	private bool leftSide;

	[SerializeField]
	private GameObject m_stickerGo;

	[SerializeField]
	private UITexture m_stickerTexture;

	[SerializeField]
	private GameObject m_stickerBlockIconGo;

	[SerializeField]
	private UILabelLocalized m_stickerBlockButtonLabel;

	[SerializeField]
	private UISprite m_stickerBgSprite;

	private bool m_voiceChatBlock;

	[SerializeField]
	private GameObject m_voiceChatBlockIconGo;

	[SerializeField]
	private GameObject m_speakingSpriteGo;

	[SerializeField]
	private UILabelLocalized m_voiceChatBlockButtonLabel;

	[SerializeField]
	private GameObject m_blockButtonGo;

	public void Awake()
	{
	}

	public void OnEnable()
	{
	}

	public void OnDisable()
	{
	}

	public void ShowSticker(int stickerId)
	{
	}

	public void HideSticker()
	{
	}

	public void OnNameClick()
	{
	}

	public void OnBlockSticker()
	{
	}

	public void OnBlockVoiceChat()
	{
	}

	private void OnStartedSpeaking(ulong speakerUID)
	{
	}

	private void OnStoppedSpeaking(ulong speakerUID)
	{
	}

	private void AddEventHandler()
	{
	}

	private void RemoveEventHandler()
	{
	}
}
