namespace HutongGames.PlayMaker.Actions
{
	public class ArrayAdd : FsmStateAction
	{
		[RequiredField]
		public FsmArray array;

		[RequiredField]
		public FsmVar value;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoAddValue()
		{
		}
	}
}
