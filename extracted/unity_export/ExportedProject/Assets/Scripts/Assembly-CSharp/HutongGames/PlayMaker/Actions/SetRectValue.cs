namespace HutongGames.PlayMaker.Actions
{
	public class SetRectValue : FsmStateAction
	{
		[RequiredField]
		public FsmRect rectVariable;

		[RequiredField]
		public FsmRect rectValue;

		public bool everyFrame;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}
	}
}
