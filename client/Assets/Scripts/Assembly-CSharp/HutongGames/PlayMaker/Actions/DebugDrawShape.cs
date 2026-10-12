namespace HutongGames.PlayMaker.Actions
{
	public class DebugDrawShape : FsmStateAction
	{
		public enum ShapeType
		{
			Sphere = 0,
			Cube = 1,
			WireSphere = 2,
			WireCube = 3
		}

		[RequiredField]
		public FsmOwnerDefault gameObject;

		public ShapeType shape;

		public FsmColor color;

		public FsmFloat radius;

		public FsmVector3 size;

		public override void Reset()
		{
		}

		public override void OnDrawActionGizmos()
		{
		}
	}
}
