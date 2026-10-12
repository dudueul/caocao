namespace HutongGames.PlayMaker.Actions
{
	public class BoolOperator : FsmStateAction
	{
		public enum Operation
		{
			AND = 0,
			NAND = 1,
			OR = 2,
			XOR = 3
		}

		[RequiredField]
		public FsmBool bool1;

		[RequiredField]
		public FsmBool bool2;

		public Operation operation;

		[RequiredField]
		public FsmBool storeResult;

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

		private void DoBoolOperator()
		{
		}
	}
}
