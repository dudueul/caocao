using System;
using System.Collections.Generic;
using LitJson;

public class MailData : ITransmittable
{
	public enum MailType
	{
		Normal = 0,
		GM = 1,
		UPDATE_USER_NOTI = 2,
		EventCommand = 3,
		Refund = 4,
		Guild = 5,
		CustomEvent = 6,
		Toy = 7
	}

	private UserInfo m_sender;

	private List<RewardInfo> m_rewards;

	private DateTime time;

	private DateTime expireTime;

	public int srl { get; private set; }

	public int mailId { get; private set; }

	public ulong senderId { get; set; }

	public string receiverName { get; set; }

	public ulong receiverId { get; set; }

	public string title { get; set; }

	public string message { get; set; }

	public MailType type { get; set; }

	public bool didRead { get; private set; }

	public bool didGotItem { get; private set; }

	public UserInfo sender
	{
		get
		{
			return null;
		}
	}

	public List<RewardInfo> rewards
	{
		get
		{
			return null;
		}
	}

	public string sendTime
	{
		get
		{
			return null;
		}
	}

	public TimeSpan leftTime
	{
		get
		{
			return default(TimeSpan);
		}
	}

	public bool isExpired
	{
		get
		{
			return false;
		}
	}

	public MailData()
	{
	}

	public MailData(ulong from)
	{
	}

	public MailData(JsonData jsonData)
	{
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public void UpdateSenderInfo(UnitUI unitUi, UILabelLocalized from, NationFlagUI nationIcon, bool translateNationName)
	{
	}

	public void Read()
	{
	}

	public void GotItem()
	{
	}

	public static int Sort(MailData x, MailData y)
	{
		return 0;
	}
}
