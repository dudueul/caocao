namespace HutongGames.PlayMaker.Actions
{
	public class SetBoolValue : FsmStateAction
	{
		[RequiredField]
		public FsmBool boolVariable;

		[RequiredField]
		public FsmBool boolValue;

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
