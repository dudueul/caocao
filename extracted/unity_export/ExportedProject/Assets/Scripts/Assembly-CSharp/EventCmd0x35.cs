using System;
using System.Xml;
using LitJson;

[Serializable]
public class EventCmd0x35 : EventCmdBase
{
	public static readonly string ms_code;

	public TargetInfo targetUnitInfo;

	public UnitInfo targetInfo;

	public bool applyOneTime;

	private int targetUnitId;

	private int beforeLookUnitId;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	public override void _Apply()
	{
	}

	public override void _Revert()
	{
	}

	private void ChangeUnitImage(bool isRecover)
	{
	}

	private void ChangeUnitImageOnDuel(int lookUnitId)
	{
	}

	private void OnResult(JsonData response)
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
