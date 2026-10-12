using System.Runtime.InteropServices;

namespace M
{
	[StructLayout((LayoutKind)0, Size = 24)]
	public struct MHsvColor
	{
		public double H;

		public double S;

		public double V;

		public MHsvColor(double h, double s, double v)
		{
			H = 0.0;
			S = 0.0;
			V = 0.0;
		}

		public override string ToString()
		{
			return null;
		}
	}
}
