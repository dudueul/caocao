namespace HutongGames.PlayMaker.Actions
{
	public class ArrayAddRange : FsmStateAction
	{
		[RequiredField]
		public FsmArray array;

		[RequiredField]
		public FsmVar[] variables;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoAddRange()
		{
		}
	}
}
