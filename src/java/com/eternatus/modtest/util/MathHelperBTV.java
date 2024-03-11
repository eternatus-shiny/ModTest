package com.eternatus.modtest.util;

import com.google.common.collect.Sets;
import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.world.entity.Entity;

import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.util.Mth;
import java.util.Set;

public class MathHelperMT {

    public static int clamp(int min, int max, int val) {
        return val < min ? min : (val > max ? max : val);
    }

    public static float clamp(float min, float max, float val) {
        return val < min ? min : (val > max ? max : val);
    }

    public static double clamp(double min, double max, double val) {
        return val < min ? min : (val > max ? max : val);
    }

    public static long clamp(long min, long max, long val) {
        return val < min ? min : (val > max ? max : val);
    }

    public static double angleBetween(Entity source, Entity target) {
        return angleBetween(source.xOld, source.zOld, target.xOld, target.zOld);
    }

    public static double angleBetween(double sourceX, double sourceZ, double targetX, double targetZ) {
        double xDiff = targetX - sourceX;
        double zDiff = targetZ - sourceZ;
        return Math.atan2(-xDiff, zDiff)*180/Math.PI;
    }

    public static float sampleNoise3D(int x, int y, int z, float simplexSampleRate) {
        return (float) ((MTSimplexNoise.noise((x + simplexSampleRate) / simplexSampleRate, (y + simplexSampleRate) / simplexSampleRate, (z + simplexSampleRate) / simplexSampleRate)));
    }
    public static float sampleNoise3D(float x, float y, float z, float simplexSampleRate) {
        return (float) ((MTSimplexNoise.noise((x + simplexSampleRate) / simplexSampleRate, (y + simplexSampleRate) / simplexSampleRate, (z + simplexSampleRate) / simplexSampleRate)));
    }

    public static float sampleNoise2D(int x, int z, float simplexSampleRate) {
        return (float) ((MTSimplexNoise.noise((x + simplexSampleRate) / simplexSampleRate, (z + simplexSampleRate) / simplexSampleRate)));
    }

    /** Returns the closest entity the player is looking at
     *
     * @param p The player
     * @param dis The maximum distance the entity can be at
     */
//    public static LivingEntity getClosestLookedAtEntity(Player p, double dis) {
//        return getClosestLookedAtEntity(p, dis, e -> true);
//    }
//
//    /** Returns the closest entity the player is looking at
//     *
//     * @param p The player
//     * @param dis The maximum distance the entity can be at
//     * @param predIn A condition that must be true for the looked at entities to be considered
//     */
//    public static LivingEntity getClosestLookedAtEntity(Player p, double dis, Predicate<LivingEntity> predIn) {
//        List<LivingEntity> ents = getLookedAtEntities(p, dis, predIn);
//        if(ents.isEmpty()) return null;
//        double minDist = ents.get(0).getDistanceSq(p);
//        int selectedEntity = 0;
//        for(int i = 1; i < ents.size(); i++) {
//            LivingEntity e = ents.get(i);
//            double newDist = e.getDistanceSq(p);
//            if(newDist < minDist) {
//                minDist = newDist;
//                selectedEntity = i;
//            }
//        }
//        return ents.get(selectedEntity);
//    }
//
//    /** Returns the closest entity the player is looking at
//     *
//     * @param p The player
//     * @param dis The maximum distance the entity can be at
//     * @param predIn A condition that must be true for the looked at entities to be considered
//     */
//    public static LivingEntity getClosestLookedAtEntity(Player p, double dis, Predicate<LivingEntity> predIn) {
//        List<LivingEntity> ents = getLookedAtEntities(p, dis, predIn);
//        if(ents.isEmpty()) return null;
//        double minDist = ents.get(0).getDistanceSq(p);
//        int selectedEntity = 0;
//        for(int i = 1; i < ents.size(); i++) {
//            LivingEntity e = ents.get(i);
//            double newDist = e.getDistanceSq(p);
//            if(newDist < minDist) {
//                minDist = newDist;
//                selectedEntity = i;
//            }
//        }
//        return ents.get(selectedEntity);
//    }
//
//    /** Returns a List of entities the player is looking at
//     *
//     * @param p The player
//     * @param dis The maximum distance the entities can be at
//     * @return
//     */
//    public static List<LivingEntity> getLookedAtEntities(Player p, double dis, Predicate<LivingEntity> predIn){
//        Predicate<LivingEntity> pred;
//        if(predIn != null)
//            pred = e -> e.getDistanceSq(p) < dis*dis && predIn.apply(e);
//        else
//            pred = e -> e.getDistanceSq(p) < dis*dis;
//        List<LivingEntity> ents = p.level.getEntities(LivingEntity.class, pred);
//
//        return getLookedAtEntities(p, ents);
//
//    }
//
//    /** Returns a List of entities the player is looking at, filtering from the passed list.
//     *
//     * @param p The player
//     * @param ents The entities that are to be checked
//     * @return
//     */
//    public static List<LivingEntity> getLookedAtEntities(Player p, List<LivingEntity> ents){
//        List<LivingEntity> list = Lists.newArrayList();
//        Vec3d lookVec = p.getLook(1.0F);
//        BlockPos pos = p.getPosition();
//        for(Entity e : ents) {
//            if(getIntersectionLineBoundingBox(pos, lookVec, e.getEntityBoundingBox().grow(1.0D)) != null) {
//                list.add((EntityLivingBase)e);
//            }
//        }
//        return list;
//    }

    private static class MTSimplexNoise {
        private static final Grad[] grad3 = {new Grad(1, 1, 0), new Grad(-1, 1, 0), new Grad(1, -1, 0), new Grad(-1, -1, 0),
                new Grad(1, 0, 1), new Grad(-1, 0, 1), new Grad(1, 0, -1), new Grad(-1, 0, -1),
                new Grad(0, 1, 1), new Grad(0, -1, 1), new Grad(0, 1, -1), new Grad(0, -1, -1)};

        private static final short[] p = {151, 160, 137, 91, 90, 15,
                131, 13, 201, 95, 96, 53, 194, 233, 7, 225, 140, 36, 103, 30, 69, 142, 8, 99, 37, 240, 21, 10, 23,
                190, 6, 148, 247, 120, 234, 75, 0, 26, 197, 62, 94, 252, 219, 203, 117, 35, 11, 32, 57, 177, 33,
                88, 237, 149, 56, 87, 174, 20, 125, 136, 171, 168, 68, 175, 74, 165, 71, 134, 139, 48, 27, 166,
                77, 146, 158, 231, 83, 111, 229, 122, 60, 211, 133, 230, 220, 105, 92, 41, 55, 46, 245, 40, 244,
                102, 143, 54, 65, 25, 63, 161, 1, 216, 80, 73, 209, 76, 132, 187, 208, 89, 18, 169, 200, 196,
                135, 130, 116, 188, 159, 86, 164, 100, 109, 198, 173, 186, 3, 64, 52, 217, 226, 250, 124, 123,
                5, 202, 38, 147, 118, 126, 255, 82, 85, 212, 207, 206, 59, 227, 47, 16, 58, 17, 182, 189, 28, 42,
                223, 183, 170, 213, 119, 248, 152, 2, 44, 154, 163, 70, 221, 153, 101, 155, 167, 43, 172, 9,
                129, 22, 39, 253, 19, 98, 108, 110, 79, 113, 224, 232, 178, 185, 112, 104, 218, 246, 97, 228,
                251, 34, 242, 193, 238, 210, 144, 12, 191, 179, 162, 241, 81, 51, 145, 235, 249, 14, 239, 107,
                49, 192, 214, 31, 181, 199, 106, 157, 184, 84, 204, 176, 115, 121, 50, 45, 127, 4, 150, 254,
                138, 236, 205, 93, 222, 114, 67, 29, 24, 72, 243, 141, 128, 195, 78, 66, 215, 61, 156, 180};
        // To remove the need for index wrapping, double the permutation table length
        private static final short[] perm = new short[512];
        private static final short[] permMod12 = new short[512];

        static {
            for (int i = 0; i < 512; i++) {
                perm[i] = p[i & 255];
                permMod12[i] = (short) (perm[i] % 12);
            }
        }
        private static final double F2 = 0.5 * (Math.sqrt(3.0) - 1.0);
        private static final double G2 = (3.0 - Math.sqrt(3.0)) / 6.0;
        private static final double F3 = 1.0 / 3.0;
        private static final double G3 = 1.0 / 6.0;
        private static final double F4 = (Math.sqrt(5.0) - 1.0) / 4.0;
        private static final double G4 = (5.0 - Math.sqrt(5.0)) / 20.0;

        // This method is a *lot* faster than using (int)Math.floor(x)
        private static int fastfloor(double x) {
            int xi = (int) x;
            return x < xi ? xi - 1 : xi;
        }

        private static double dot(Grad g, double x, double y) {
            return g.x * x + g.y * y;
        }

        private static double dot(Grad g, double x, double y, double z) {
            return g.x * x + g.y * y + g.z * z;
        }

        private static double dot(Grad g, double x, double y, double z, double w) {
            return g.x * x + g.y * y + g.z * z + g.w * w;
        }
        private static class Grad {
            double x, y, z, w;

            Grad(double x, double y, double z) {
                this.x = x;
                this.y = y;
                this.z = z;
            }

            Grad(double x, double y, double z, double w) {
                this.x = x;
                this.y = y;
                this.z = z;
                this.w = w;
            }
        }

        public static double noise(double xin, double yin) {
            double n0, n1, n2; // Noise contributions from the three corners
            // Skew the input space to determine which simplex cell we're in
            double s = (xin + yin) * F2; // Hairy factor for 2D
            int i = fastfloor(xin + s);
            int j = fastfloor(yin + s);
            double t = (i + j) * G2;
            double X0 = i - t; // Unskew the cell origin back to (x,y) space
            double Y0 = j - t;
            double x0 = xin - X0; // The x,y distances from the cell origin
            double y0 = yin - Y0;
            // For the 2D case, the simplex shape is an equilateral triangle.
            // Determine which simplex we are in.
            int i1, j1; // Offsets for second (middle) corner of simplex in (i,j) coords
            if (x0 > y0) {
                i1 = 1;
                j1 = 0;
            } // lower triangle, XY order: (0,0)->(1,0)->(1,1)
            else {
                i1 = 0;
                j1 = 1;
            }      // upper triangle, YX order: (0,0)->(0,1)->(1,1)
            // A step of (1,0) in (i,j) means a step of (1-c,-c) in (x,y), and
            // a step of (0,1) in (i,j) means a step of (-c,1-c) in (x,y), where
            // c = (3-sqrt(3))/6
            double x1 = x0 - i1 + G2; // Offsets for middle corner in (x,y) unskewed coords
            double y1 = y0 - j1 + G2;
            double x2 = x0 - 1.0 + 2.0 * G2; // Offsets for last corner in (x,y) unskewed coords
            double y2 = y0 - 1.0 + 2.0 * G2;
            // Work out the hashed gradient indices of the three simplex corners
            int ii = i & 255;
            int jj = j & 255;
            int gi0 = permMod12[ii + perm[jj]];
            int gi1 = permMod12[ii + i1 + perm[jj + j1]];
            int gi2 = permMod12[ii + 1 + perm[jj + 1]];
            // Calculate the contribution from the three corners
            double t0 = 0.5 - x0 * x0 - y0 * y0;
            if (t0 < 0) n0 = 0.0;
            else {
                t0 *= t0;
                n0 = t0 * t0 * dot(grad3[gi0], x0, y0);  // (x,y) of grad3 used for 2D gradient
            }
            double t1 = 0.5 - x1 * x1 - y1 * y1;
            if (t1 < 0) n1 = 0.0;
            else {
                t1 *= t1;
                n1 = t1 * t1 * dot(grad3[gi1], x1, y1);
            }
            double t2 = 0.5 - x2 * x2 - y2 * y2;
            if (t2 < 0) n2 = 0.0;
            else {
                t2 *= t2;
                n2 = t2 * t2 * dot(grad3[gi2], x2, y2);
            }
            // Add contributions from each corner to get the final noise value.
            // The result is scaled to return values in the interval [-1,1].
            return 70.0 * (n0 + n1 + n2);
        }
        public static double noise(double xin, double yin, double zin) {
            double n0, n1, n2, n3; // Noise contributions from the four corners
            // Skew the input space to determine which simplex cell we're in
            double s = (xin + yin + zin) * F3; // Very nice and simple skew factor for 3D
            int i = fastfloor(xin + s);
            int j = fastfloor(yin + s);
            int k = fastfloor(zin + s);
            double t = (i + j + k) * G3;
            double X0 = i - t; // Unskew the cell origin back to (x,y,z) space
            double Y0 = j - t;
            double Z0 = k - t;
            double x0 = xin - X0; // The x,y,z distances from the cell origin
            double y0 = yin - Y0;
            double z0 = zin - Z0;
            // For the 3D case, the simplex shape is a slightly irregular tetrahedron.
            // Determine which simplex we are in.
            int i1, j1, k1; // Offsets for second corner of simplex in (i,j,k) coords
            int i2, j2, k2; // Offsets for third corner of simplex in (i,j,k) coords
            if (x0 >= y0) {
                if (y0 >= z0) {
                    i1 = 1;
                    j1 = 0;
                    k1 = 0;
                    i2 = 1;
                    j2 = 1;
                    k2 = 0;
                } // X Y Z order
                else if (x0 >= z0) {
                    i1 = 1;
                    j1 = 0;
                    k1 = 0;
                    i2 = 1;
                    j2 = 0;
                    k2 = 1;
                } // X Z Y order
                else {
                    i1 = 0;
                    j1 = 0;
                    k1 = 1;
                    i2 = 1;
                    j2 = 0;
                    k2 = 1;
                } // Z X Y order
            } else { // x0<y0
                if (y0 < z0) {
                    i1 = 0;
                    j1 = 0;
                    k1 = 1;
                    i2 = 0;
                    j2 = 1;
                    k2 = 1;
                } // Z Y X order
                else if (x0 < z0) {
                    i1 = 0;
                    j1 = 1;
                    k1 = 0;
                    i2 = 0;
                    j2 = 1;
                    k2 = 1;
                } // Y Z X order
                else {
                    i1 = 0;
                    j1 = 1;
                    k1 = 0;
                    i2 = 1;
                    j2 = 1;
                    k2 = 0;
                } // Y X Z order
            }
            // A step of (1,0,0) in (i,j,k) means a step of (1-c,-c,-c) in (x,y,z),
            // a step of (0,1,0) in (i,j,k) means a step of (-c,1-c,-c) in (x,y,z), and
            // a step of (0,0,1) in (i,j,k) means a step of (-c,-c,1-c) in (x,y,z), where
            // c = 1/6.
            double x1 = x0 - i1 + G3; // Offsets for second corner in (x,y,z) coords
            double y1 = y0 - j1 + G3;
            double z1 = z0 - k1 + G3;
            double x2 = x0 - i2 + 2.0 * G3; // Offsets for third corner in (x,y,z) coords
            double y2 = y0 - j2 + 2.0 * G3;
            double z2 = z0 - k2 + 2.0 * G3;
            double x3 = x0 - 1.0 + 3.0 * G3; // Offsets for last corner in (x,y,z) coords
            double y3 = y0 - 1.0 + 3.0 * G3;
            double z3 = z0 - 1.0 + 3.0 * G3;
            // Work out the hashed gradient indices of the four simplex corners
            int ii = i & 255;
            int jj = j & 255;
            int kk = k & 255;
            int gi0 = permMod12[ii + perm[jj + perm[kk]]];
            int gi1 = permMod12[ii + i1 + perm[jj + j1 + perm[kk + k1]]];
            int gi2 = permMod12[ii + i2 + perm[jj + j2 + perm[kk + k2]]];
            int gi3 = permMod12[ii + 1 + perm[jj + 1 + perm[kk + 1]]];
            // Calculate the contribution from the four corners
            double t0 = 0.6 - x0 * x0 - y0 * y0 - z0 * z0;
            if (t0 < 0) n0 = 0.0;
            else {
                t0 *= t0;
                n0 = t0 * t0 * dot(grad3[gi0], x0, y0, z0);
            }
            double t1 = 0.6 - x1 * x1 - y1 * y1 - z1 * z1;
            if (t1 < 0) n1 = 0.0;
            else {
                t1 *= t1;
                n1 = t1 * t1 * dot(grad3[gi1], x1, y1, z1);
            }
            double t2 = 0.6 - x2 * x2 - y2 * y2 - z2 * z2;
            if (t2 < 0) n2 = 0.0;
            else {
                t2 *= t2;
                n2 = t2 * t2 * dot(grad3[gi2], x2, y2, z2);
            }
            double t3 = 0.6 - x3 * x3 - y3 * y3 - z3 * z3;
            if (t3 < 0) n3 = 0.0;
            else {
                t3 *= t3;
                n3 = t3 * t3 * dot(grad3[gi3], x3, y3, z3);
            }
            // Add contributions from each corner to get the final noise value.
            // The result is scaled to stay just inside [-1,1]
            return 32.0 * (n0 + n1 + n2 + n3);
        }
    }
    public static Set<Holder<Biome>> getBiomesWithinAtY(BiomeSource biomeSource, int x, int y, int z, int xzDist, Climate.Sampler sampler) {
        int i = QuartPos.fromBlock(x - xzDist);
        int j = QuartPos.fromBlock(y);
        int k = QuartPos.fromBlock(z - xzDist);
        int l = QuartPos.fromBlock(x + xzDist);
        int j1 = QuartPos.fromBlock(z + xzDist);
        int k1 = l - i + 1;
        int i2 = j1 - k + 1;
        Set<Holder<Biome>> set = Sets.newHashSet();

        for(int j2 = 0; j2 < i2; ++j2) {
            for(int k2 = 0; k2 < k1; ++k2) {
                int i3 = i + k2;
                int k3 = k + j2;
                set.add(biomeSource.getNoiseBiome(i3, j, k3, sampler));
            }
        }
        return set;
    }
}
