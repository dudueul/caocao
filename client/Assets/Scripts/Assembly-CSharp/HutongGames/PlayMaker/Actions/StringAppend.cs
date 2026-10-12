namespace HutongGames.PlayMaker.Actions
{
	public class StringAppend : FsmStateAction
	{
		[RequiredField]
		public FsmString stringVariable;

		public FsmString appendString;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}
	}
}
