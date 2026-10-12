public abstract class ShortCutDefault
{
	protected internal EnumData.ShortCutType _shortCutTypeId { get; set; }

	protected internal int _data { get; set; }

	public abstract bool ShortCut();

	public virtual bool CheckCondition()
	{
		return false;
	}
}
