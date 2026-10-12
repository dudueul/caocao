namespace HutongGames.PlayMaker.Actions
{
	public class IntOperator : FsmStateAction
	{
		public enum Operation
		{
			Add = 0,
			Subtract = 1,
			Multiply = 2,
			Divide = 3,
			Min = 4,
			Max = 5
		}

		[RequiredField]
		public FsmInt integer1;

		[RequiredField]
		public FsmInt integer2;

		public Operation operation;

		[RequiredField]
		public FsmInt storeResult;

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

		private void DoIntOperator()
		{
		}
	}
}
